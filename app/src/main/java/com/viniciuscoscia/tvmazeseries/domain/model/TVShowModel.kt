package com.viniciuscoscia.tvmazeseries.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShowModel(
    val id: Int,
    val name: String,
    val imageSmallUrl: String,
    val imageUrl: String,
    val premiered: String?,
    val ended: String?,
    val genres: List<String>?,
    val summary: String?
) : Parcelable