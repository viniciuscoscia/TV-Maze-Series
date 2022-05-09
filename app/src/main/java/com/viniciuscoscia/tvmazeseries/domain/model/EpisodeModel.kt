package com.viniciuscoscia.tvmazeseries.domain.model

data class EpisodeModel(
    val id: Int,
    val name: String,
    val image: String?,
    val number: Int,
    val summary: String?,
    val season: Int
)