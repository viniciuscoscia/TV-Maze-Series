package com.viniciuscoscia.tvmazeseries.data.remote.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class TVSeriesModelResponseItem(
    val averageRuntime: Int?,
    val dvdCountry: Any?,
    val ended: String?,
    val externals: Externals,
    val genres: List<String>,
    val id: Int,
    val image: Image,
    val language: String,
    @Json(name = "_links")
    val links: Links,
    val name: String,
    val network: Network?,
    val officialSite: String?,
    val premiered: String?,
    val rating: Rating,
    val runtime: Int?,
    val schedule: Schedule,
    val status: String,
    val summary: String,
    val type: String,
    val updated: Int,
    val url: String,
    val webChannel: WebChannel?,
    val weight: Int
)