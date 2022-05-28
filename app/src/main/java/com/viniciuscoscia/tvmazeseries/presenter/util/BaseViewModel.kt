package com.viniciuscoscia.tvmazeseries.presenter.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viniciuscoscia.tvmazeseries.presenter.ui.composables.ErrorDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    private val _errorState: MutableStateFlow<ErrorState> = MutableStateFlow(ErrorState.NoError)
    val errorState = _errorState
        .asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000), // Or Lazily because it's a one-shot
            initialValue = ErrorState.NoError
        )

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
fun BaseViewModel.ObserveErrorState(onConfirmButtonClicked: (() -> Unit)? = null) {
    ErrorDialog(
        errorState = errorState.collectAsState().value,
        onConfirmButtonClicked = onConfirmButtonClicked
    ) {
        dismissError()
    }
}