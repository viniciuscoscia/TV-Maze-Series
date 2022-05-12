package com.viniciuscoscia.tvmazeseries.presenter.ui.searchscreen

import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel

data class TVShowSearchUiModel(
    val shows: List<TVShowModel>? = null,
    val loading: Boolean = true
)