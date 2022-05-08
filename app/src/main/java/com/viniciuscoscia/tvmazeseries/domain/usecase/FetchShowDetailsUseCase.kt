package com.viniciuscoscia.tvmazeseries.domain.usecase

import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel

class FetchShowDetailsUseCase(
    private val repository: TVMazeRepository
) {
    suspend operator fun invoke(showId: Int): TVShowModel {
        return repository.getShowDetails(showId)
    }
}