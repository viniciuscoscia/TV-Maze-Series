package com.viniciuscoscia.tvmazeseries.domain.usecase

import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class SearchShowByNameUseCase(
    private val repository: TVMazeRepository
) {
    suspend operator fun invoke(showName: String): List<TVShowModel> = withContext(Dispatchers.IO) {
        try {
            repository.searchShowByNameUseCase(showName)
        } catch (exception: Exception) {
            Timber.e(exception)
            emptyList()
        }
    }
}