package com.viniciuscoscia.tvmazeseries.data.remote.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Schedule(
    val days: List<String>,
    val time: String
)