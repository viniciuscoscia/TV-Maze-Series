package com.viniciuscoscia.tvmazeseries.data.remote.api

import com.viniciuscoscia.tvmazeseries.data.remote.entity.episodes.EpisodeResponseItem
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
        ) showId: Int
    ): Response<TVShowResponseModel>

    @GET("$BASE_SHOWS_ENDPOINT/{$SHOW_ID}/episodes")
    suspend fun getTvShowEpisodes(
        @Path(
            value = SHOW_ID,
            encoded = true
        ) showId: Int
    ): Response<List<EpisodeResponseItem>>

    @GET("$BASE_EPISODES_ENDPOINT/{$EPISODE_ID}")
    suspend fun getEpisodeDetails(
        @Path(
            value = EPISODE_ID,
            encoded = true
        ) episodeId: Int
    ): Response<EpisodeResponseItem>

    companion object {
        private const val BASE_SHOWS_ENDPOINT = "shows"
        private const val BASE_EPISODES_ENDPOINT = "episodes"
        private const val SHOW_ID = "showId"
        private const val EPISODE_ID = "episodeId"
    }
}