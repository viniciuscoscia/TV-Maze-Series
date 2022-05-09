package com.viniciuscoscia.tvmazeseries.data.remote.entity.episodes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.viniciuscoscia.tvmazeseries.domain.model.EpisodeModel
import com.viniciuscoscia.tvmazeseries.domain.model.SeasonModel

@JsonClass(generateAdapter = true)
data class EpisodeListResponseItem(
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: Image?,
    @Json(name = "_links")
    val links: Links,
    val name: String,
    val number: Int,
    val rating: Rating,
    val runtime: Int,
    val season: Int,
    val summary: String?,
    val type: String,
    val url: String
) {
    @JsonClass(generateAdapter = true)
    data class Image(
        val medium: String?,
        val original: String?
    )

    @JsonClass(generateAdapter = true)
    data class Links(
        val self: Self
    ) {
        @JsonClass(generateAdapter = true)
        data class Self(
            val href: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class Rating(
        val average: Any?
    )
}

fun EpisodeListResponseItem.toDomain() = EpisodeModel(
    id = id,
    name = name,
    season = season,
    image = image?.original,
    number = number,
    summary = summary
)

fun List<EpisodeListResponseItem>.toDomain(): List<SeasonModel> {
    val seasons = arrayListOf<SeasonModel>()

    map { it.season }
        .distinct()
        .forEach { season ->
            val episodes = filter { it.season == season }.map { it.toDomain() }
            seasons.add(SeasonModel(season, episodes))
        }

    return seasons
}