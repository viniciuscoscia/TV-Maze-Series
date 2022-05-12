package com.viniciuscoscia.tvmazeseries.domain.usecase

import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.domain.model.SeasonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class FetchShowEpisodeListUseCase(
    private val repository: TVMazeRepository
) {
    suspend operator fun invoke(showId: Int): List<SeasonModel> = withContext(Dispatchers.IO) {
        try {
            repository.getShowEpisodeList(showId)
        } catch (exception: Exception) {
            Timber.e(exception)
            listOf()
        }
    }
}