package com.viniciuscoscia.tvmazeseries.domain.usecase

import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchShowsByPageUseCase(private val repository: TVMazeRepository) {
    suspend operator fun invoke(page: Int): List<TVShowModel> = withContext(Dispatchers.IO) {
        repository.getShowsByPageNumber(page)
    }
}