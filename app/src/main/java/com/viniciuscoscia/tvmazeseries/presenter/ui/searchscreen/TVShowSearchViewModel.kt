package com.viniciuscoscia.tvmazeseries.presenter.ui.searchscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.domain.usecase.SearchShowByNameUseCase
import com.viniciuscoscia.tvmazeseries.presenter.util.KoinViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class TVShowSearchViewModel : KoinViewModel() {
    private val searchShowByNameUseCase: SearchShowByNameUseCase by inject()
    val shows = mutableStateOf<List<TVShowModel>>(emptyList())

    fun searchForShowByName(showName: String) = viewModelScope.launch {
        shows.value = searchShowByNameUseCase(showName)
    }
}