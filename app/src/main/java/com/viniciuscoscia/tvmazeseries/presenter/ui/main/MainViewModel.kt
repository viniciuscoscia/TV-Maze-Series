package com.viniciuscoscia.tvmazeseries.presenter.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.domain.usecase.FetchShowsUseCase
import com.viniciuscoscia.tvmazeseries.presenter.util.BaseViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.inject

class MainViewModel : BaseViewModel() {
    private val fetchShowsByPageUseCase: FetchShowsUseCase by inject()

    fun getTvShows(): Flow<PagingData<TVShowModel>> {
        return fetchShowsByPageUseCase().cachedIn(viewModelScope)
    }

    fun onPagingError() {
        showError("Error fetching TV Shows")
    }
}