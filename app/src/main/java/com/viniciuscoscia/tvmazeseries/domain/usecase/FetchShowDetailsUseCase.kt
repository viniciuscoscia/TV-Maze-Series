package com.viniciuscoscia.tvmazeseries.domain.usecase

import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchShowDetailsUseCase(
    private val repository: TVMazeRepository
) {
    suspend operator fun invoke(showId: Int): TVShowModel = withContext(Dispatchers.IO) {
        repository.getShowDetails(showId)
    }
}