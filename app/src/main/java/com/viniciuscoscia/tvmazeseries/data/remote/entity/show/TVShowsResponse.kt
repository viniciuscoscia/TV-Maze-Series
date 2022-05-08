package com.viniciuscoscia.tvmazeseries.data.remote.entity.show

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel

@JsonClass(generateAdapter = true)
data class TVShowResponseModel(
    val averageRuntime: Int?,
    val dvdCountry: Any?,
    val ended: String?,
    val externals: Externals,
    val genres: List<String>,
    val id: Int,
    val image: Image?,
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
) {
    @JsonClass(generateAdapter = true)
    data class Externals(
        val imdb: String?,
        val thetvdb: Int?,
        val tvrage: Int?
    )

    @JsonClass(generateAdapter = true)
    data class Image(
        val medium: String?,
        val original: String?
    )

    @JsonClass(generateAdapter = true)
    data class Links(
        val previousepisode: Previousepisode?,
        val self: Self
    ) {
        @JsonClass(generateAdapter = true)
        data class Previousepisode(
            val href: String
        )

        @JsonClass(generateAdapter = true)
        data class Self(
            val href: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class Network(
        val country: Country,
        val id: Int,
        val name: String,
        val officialSite: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Country(
            val code: String,
            val name: String,
            val timezone: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class Rating(
        val average: Double?
    )

    @JsonClass(generateAdapter = true)
    data class Schedule(
        val days: List<String>,
        val time: String
    )

    @JsonClass(generateAdapter = true)
    data class WebChannel(
        val country: Any?,
        val id: Int,
        val name: String,
        val officialSite: String?
    )
}

fun List<TVShowResponseModel>.toDomain(): List<TVShowModel> = map {
    TVShowModel(
        id = it.id,
        name = it.name,
        imageSmallUrl = it.image?.medium ?: "",
        imageUrl = it.image?.original ?: ""
    )
}
