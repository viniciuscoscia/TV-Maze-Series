package com.viniciuscoscia.tvmazeseries.data.remote.entity


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowSearchResponseItem(
    val score: Double,
    val show: TVShowResponseModel
)


fun ShowSearchResponseItem.toDomain() = show.toDomain()
fun List<ShowSearchResponseItem>.toDomain() = map { it.toDomain() }
