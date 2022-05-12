package com.viniciuscoscia.tvmazeseries.domain.usecase

import androidx.paging.PagingData
import com.viniciuscoscia.tvmazeseries.data.repository.TVMazeRepository
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import kotlinx.coroutines.flow.Flow

class FetchShowsUseCase(private val repository: TVMazeRepository) {
    operator fun invoke(): Flow<PagingData<TVShowModel>> {
        return repository.getShowsByPageNumber()
    }
}