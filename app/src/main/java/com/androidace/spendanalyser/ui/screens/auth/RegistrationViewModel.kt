package com.androidace.spendanalyser.ui.screens.auth

import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.ViewModel
import com.androidace.spendanalyser.ui.components.common.UIStateHandlerImpl
import com.androidace.spendanalyser.ui.components.common.UiStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(val uiStateImpl: UIStateHandlerImpl) : ViewModel(),
    UiStateHandler by uiStateImpl {



    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            RegisterScreenEvent.OnRegisterClick -> TODO()
            is RegisterScreenEvent.UserNameEntered -> TODO()
            is RegisterScreenEvent.UserNameFocus -> TODO()
        }
    }

}

sealed class RegisterScreenState {
}

sealed interface RegisterScreenEvent {
    data class UserNameEntered(val value: String) : RegisterScreenEvent
    data class UserNameFocus(val focusState: FocusState) : RegisterScreenEvent
    object OnRegisterClick : RegisterScreenEvent

}

