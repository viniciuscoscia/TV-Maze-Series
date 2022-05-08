package com.viniciuscoscia.tvmazeseries.data.remote.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class WebChannel(
    val country: Any?,
    val id: Int,
    val name: String,
    val officialSite: Any?
)