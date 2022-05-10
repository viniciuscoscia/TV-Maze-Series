package com.viniciuscoscia.tvmazeseries.domain.usecase

import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.domain.model.EpisodeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchEpisodeInfoUseCase(
    private val repository: TVMazeRepository
) {
    suspend operator fun invoke(episodeId: Int): EpisodeModel = withContext(Dispatchers.IO) {
        repository.getEpisodeDetails(episodeId)
    }
}