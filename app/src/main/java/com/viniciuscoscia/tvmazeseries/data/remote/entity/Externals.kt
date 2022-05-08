package com.viniciuscoscia.tvmazeseries.data.remote.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Externals(
    val imdb: String,
    val thetvdb: Int,
    val tvrage: Int
)