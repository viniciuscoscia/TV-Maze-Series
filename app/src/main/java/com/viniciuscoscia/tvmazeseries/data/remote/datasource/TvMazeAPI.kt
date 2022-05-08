package com.viniciuscoscia.tvmazeseries.data.remote.datasource

import com.viniciuscoscia.tvmazeseries.data.remote.entity.show.TVShowsResponse
import retrofit2.Response
import retrofit2.http.GET

interface TvMazeAPI {
    @GET
    suspend fun getTvShowsByPage(page: Int): Response<TVShowsResponse>
}