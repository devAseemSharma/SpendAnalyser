package com.androidace.spendanalyser.ui.screens.auth

import androidx.lifecycle.ViewModel
import com.androidace.spendanalyser.ui.components.common.SAState
import com.androidace.spendanalyser.ui.components.common.UIStateHandlerImpl
import com.androidace.spendanalyser.ui.components.common.UiStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(val uiStateImpl: UIStateHandlerImpl) : ViewModel() {
    private var _screenState = MutableStateFlow(RegisterScreenState.ScreenState(uiStateImpl))

}

sealed class RegisterScreenState {
    data class ScreenState(val uiStateImpl: UIStateHandlerImpl) : UiStateHandler by uiStateImpl
}