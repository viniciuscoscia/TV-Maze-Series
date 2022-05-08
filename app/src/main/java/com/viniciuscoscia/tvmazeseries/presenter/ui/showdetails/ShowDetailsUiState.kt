package com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails

import com.viniciuscoscia.tvmazeseries.domain.model.SeasonModel
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel

data class ShowDetailsUiState(
    val showModel: TVShowModel? = null,
    val seasons: List<SeasonModel>? = null,
    val loading: Boolean = true
)