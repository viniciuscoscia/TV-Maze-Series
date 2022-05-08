package com.viniciuscoscia.tvmazeseries.data.remote.api

import com.viniciuscoscia.tvmazeseries.data.remote.entity.show.TVShowResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVMazeAPI {
    @GET(BASE_SHOWS_ENDPOINT)
    suspend fun getTvShowsByPage(@Query("page") page: Int): Response<List<TVShowResponseModel>>

    @GET("$BASE_SHOWS_ENDPOINT/{$SHOW_ID}")
    suspend fun getTvShowDetails(
        @Path(
            value = SHOW_ID,
            encoded = true
        ) showId: String
    ): Response<TVShowResponseModel>

    @GET("$BASE_SHOWS_ENDPOINT/{$SHOW_ID}/episodes")
    suspend fun getTvShowEpisodes(
        @Path(
            value = SHOW_ID,
            encoded = true
        ) showId: String
    ): Response<TVShowResponseModel>

    companion object {
        private const val BASE_SHOWS_ENDPOINT = "shows"
        private const val SHOW_ID = "showId"
    }
}