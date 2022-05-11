package com.viniciuscoscia.tvmazeseries.presenter.ui.episodedetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.tvmazeseries.domain.model.EpisodeModel
import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchEpisodeInfoUseCase
import com.viniciuscoscia.tvmazeseries.presenter.util.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class TVShowEpisodeDetailsViewModel : BaseViewModel() {
    private val fetchEpisodeInfoUseCase: FetchEpisodeInfoUseCase by inject()
    val episodeDetails = mutableStateOf(TVShowEpisodeDetailsUIModel())

    fun fetchEpisodeInfo(episodeId: Int) = viewModelScope.launch {
        runCatching {
            episodeDetails.value =
                TVShowEpisodeDetailsUIModel(false, fetchEpisodeInfoUseCase(episodeId))
        }.recoverCatching {
            episodeDetails.value = TVShowEpisodeDetailsUIModel(false)
        }
    }
}

data class TVShowEpisodeDetailsUIModel(
    val loading: Boolean = true,
    val episodeDetails: EpisodeModel? = null
)