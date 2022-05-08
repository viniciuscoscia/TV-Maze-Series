package com.viniciuscoscia.tvmazeseries.data.repository

import com.viniciuscoscia.tvmazeseries.data.helper.NetworkException
import com.viniciuscoscia.tvmazeseries.data.helper.Outcome
import com.viniciuscoscia.tvmazeseries.data.helper.parseResponse
import com.viniciuscoscia.tvmazeseries.data.remote.datasource.TvMazeAPI
import com.viniciuscoscia.tvmazeseries.data.remote.entity.show.toDomain
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel

class TVMazeRepositoryImpl(
    private val tvMazeApi: TvMazeAPI
): TVMazeRepository {
    @Throws(NetworkException::class)
    override suspend fun getShowsByPageNumber(page: Int): List<TVShowModel> {
        return when (val outcome = tvMazeApi.getTvShowsByPage(page).parseResponse()) {
            is Outcome.Success -> outcome.value.toDomain()
            is Outcome.Failure -> throw NetworkException.parse(outcome.statusCode)
        }
    }
}

interface TVMazeRepository {
    suspend fun getShowsByPageNumber(page: Int): List<TVShowModel>
}