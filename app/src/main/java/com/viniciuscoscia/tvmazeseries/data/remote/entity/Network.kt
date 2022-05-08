package com.viniciuscoscia.tvmazeseries.data.remote.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Network(
    val country: Country,
    val id: Int,
    val name: String,
    val officialSite: String
)