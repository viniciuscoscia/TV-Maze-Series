package com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchShowDetailsUseCase
import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchShowEpisodeListUseCase
import com.viniciuscoscia.tvmazeseries.presenter.util.KoinViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class TVShowDetailsViewModel : KoinViewModel() {
    private val fetchShowDetailsUseCase: FetchShowDetailsUseCase by inject()
    private val fetchShowEpisodeListUseCase: FetchShowEpisodeListUseCase by inject()

    var episodeDetails by mutableStateOf(ShowDetailsUiState())
        private set

    fun fetchInfo(showId: Int) = viewModelScope.launch {
        val showDetails = async { fetchShowDetailsUseCase(showId) }
        val showEpisodes = async { fetchShowEpisodeListUseCase(showId) }

        episodeDetails = ShowDetailsUiState(showDetails.await(), showEpisodes.await(), false)
    }
}