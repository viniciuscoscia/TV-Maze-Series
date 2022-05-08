package com.viniciuscoscia.tvmazeseries.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingConfig.Companion.MAX_SIZE_UNBOUNDED
import androidx.paging.PagingData
import com.viniciuscoscia.tvmazeseries.data.helper.NetworkException
import com.viniciuscoscia.tvmazeseries.data.helper.Outcome
import com.viniciuscoscia.tvmazeseries.data.helper.parseResponse
import com.viniciuscoscia.tvmazeseries.data.remote.api.TVMazeAPI
import com.viniciuscoscia.tvmazeseries.data.remote.datasource.TVMazeSeriesPagingSource
import com.viniciuscoscia.tvmazeseries.data.remote.entity.show.toDomain
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import kotlinx.coroutines.flow.Flow

class TVMazeRepositoryImpl(
    private val tvMazeApi: TVMazeAPI
) : TVMazeRepository {
    override fun getShowsByPageNumber(): Flow<PagingData<TVShowModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = MAX_SIZE_UNBOUNDED),
            pagingSourceFactory = { TVMazeSeriesPagingSource(tvMazeApi) }
        ).flow
    }

    @Throws(NetworkException::class)
    override suspend fun getShowDetails(showId: String): TVShowModel {
        return when (val outcome = tvMazeApi.getTvShowDetails(showId).parseResponse()) {
            is Outcome.Success -> outcome.value.toDomain()
            is Outcome.Failure -> throw NetworkException.parse(outcome.statusCode)
        }
    }

    @Throws(NetworkException::class)
    override suspend fun getShowEpisodeList(showId: String): TVShowModel {
        return when (val outcome = tvMazeApi.getTvShowEpisodes(showId).parseResponse()) {
            is Outcome.Success -> outcome.value.toDomain()
            is Outcome.Failure -> throw NetworkException.parse(outcome.statusCode)
        }
    }
}

interface TVMazeRepository {
    fun getShowsByPageNumber(): Flow<PagingData<TVShowModel>>
    suspend fun getShowDetails(showId: String): TVShowModel
    suspend fun getShowEpisodeList(showId: String): TVShowModel
}