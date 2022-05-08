package com.viniciuscoscia.tvmazeseries.data.remote.api

import com.viniciuscoscia.tvmazeseries.data.remote.entity.show.TVShowResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVMazeAPI {
    @GET(BASE_SHOWS_ENDPOINT)
    suspend fun getTvShowsByPage(@Query("page") page: Int): Response<List<TVShowResponseModel>>

    companion object {
        private const val BASE_SHOWS_ENDPOINT = "shows"
    }
}