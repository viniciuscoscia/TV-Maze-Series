package com.viniciuscoscia.tvmazeseries.presenter.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.viniciuscoscia.tvmazeseries.presenter.ui.composables.ErrorDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    private val _errorState: MutableStateFlow<ErrorState> = MutableStateFlow(ErrorState.NoError)
    val errorState = _errorState.asStateFlow()

    protected fun showError(message: String, title: String = "Error") {
        _errorState.value = ErrorState.Error(title, message)
    }

    fun dismissError() {
        _errorState.value = ErrorState.NoError
    }
}

sealed interface ErrorState {
    object NoError : ErrorState
    class Error(val title: String = "Error", val message: String) : ErrorState
}

@Composable
fun BaseViewModel.ObserveErrorState() {
    ErrorDialog(errorState = errorState.collectAsState().value) {
        dismissError()
    }
}