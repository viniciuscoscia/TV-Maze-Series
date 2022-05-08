package com.viniciuscoscia.tvmazeseries.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.viniciuscoscia.tvmazeseries.data.remote.api.TVMazeAPI
import com.viniciuscoscia.tvmazeseries.data.remote.entity.show.toDomain
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import retrofit2.HttpException
import java.io.IOException

class TVMazeSeriesPagingSource(
    private val api: TVMazeAPI
) : PagingSource<Int, TVShowModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowModel> {
        return try {
            val result = api.getTvShowsByPage(params.key ?: STARTING_PAGE_INDEX).body()!!.toDomain()

            LoadResult.Page(
                data = result,
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, TVShowModel>): Int? {
        return state.anchorPosition
    }
}