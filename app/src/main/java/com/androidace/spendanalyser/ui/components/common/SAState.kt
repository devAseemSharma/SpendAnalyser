package com.androidace.spendanalyser.ui.components.common

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class SAState(
    val isLoading: Boolean = false
)

@ViewModelScoped
class UIStateHandlerImpl @Inject constructor(): UiStateHandler{
    private val _uiStateFlow = MutableStateFlow(
        SAState()
    )

    override val uiStateFlow: StateFlow<SAState>
        get() = _uiStateFlow

    override fun hideLoader() {
        _uiStateFlow.value = uiStateFlow.value.copy(isLoading = false)
    }

    override fun showLoader() {
        _uiStateFlow.value = uiStateFlow.value.copy(isLoading = true)
    }

    override fun updateState(eJUiState: SAState) {
        _uiStateFlow.value = eJUiState
    }

}

interface UiStateHandler {
    val uiStateFlow: StateFlow<SAState>
    fun hideLoader()
    fun showLoader()
    fun updateState(eJUiState: SAState)
}