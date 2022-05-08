package com.viniciuscoscia.tvmazeseries.domain.usecase

import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.domain.model.SeasonModel

class FetchShowEpisodeListUseCase(
    private val repository: TVMazeRepository
) {
    suspend operator fun invoke(showId: Int): List<SeasonModel> {
        return repository.getShowEpisodeList(showId)
    }
}