package com.viniciuscoscia.tvmazeseries.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingConfig.Companion.MAX_SIZE_UNBOUNDED
import androidx.paging.PagingData
import com.viniciuscoscia.tvmazeseries.data.remote.api.TVMazeAPI
import com.viniciuscoscia.tvmazeseries.data.remote.datasource.TVMazeSeriesPagingSource
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import kotlinx.coroutines.flow.Flow

class TVMazeRepositoryImpl(
    private val tvMazeApi: TVMazeAPI
): TVMazeRepository {
    override fun getShowsByPageNumber(): Flow<PagingData<TVShowModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = MAX_SIZE_UNBOUNDED),
            pagingSourceFactory = { TVMazeSeriesPagingSource(tvMazeApi) }
        ).flow
    }

//    @Throws(NetworkException::class)
//    override suspend fun getShowsByPageNumber(page: Int): List<TVShowModel> {
//        return when (val outcome = tvMazeApi.getTvShowsByPage(page).parseResponse()) {
//            is Outcome.Success -> outcome.value.toDomain()
//            is Outcome.Failure -> throw NetworkException.parse(outcome.statusCode)
//        }
//    }
}

interface TVMazeRepository {
    fun getShowsByPageNumber(): Flow<PagingData<TVShowModel>>
}