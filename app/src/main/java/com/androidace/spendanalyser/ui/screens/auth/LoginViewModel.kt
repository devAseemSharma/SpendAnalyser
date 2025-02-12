package com.androidace.spendanalyser.ui.screens.auth

import androidx.lifecycle.ViewModel
import com.androidace.spendanalyser.ui.components.common.UIStateHandlerImpl
import com.androidace.spendanalyser.ui.components.common.UiStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val uiStateHandlerImpl: UIStateHandlerImpl
) : ViewModel(), UiStateHandler by uiStateHandlerImpl {

    private var _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.PasswordEntered -> onPasswordEntered(value = event.value)
            is LoginScreenEvent.UserNameEntered -> onUserNameEntered(value = event.value)

            is LoginScreenEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun onLoginClick() {

    }

    private fun onUserNameEntered(value: String) {
        _loginScreenState.update {
            it.copy(userName = value)
        }
        validateUserName()
    }

    private fun onPasswordEntered(value: String) {
        _loginScreenState.update {
            it.copy(password = value)
        }
        validatePassword()
    }

    private fun validateUserName() {
        if (_loginScreenState.value.userName.isEmpty()) {
            _loginScreenState.update {
                it.copy(userNameError = "User name cannot be empty")
            }
            return
        }

        _loginScreenState.update {
            it.copy(userNameError = "")
        }
    }

    private fun validatePassword() {
        if (_loginScreenState.value.password.isEmpty()) {
            _loginScreenState.update {
                it.copy(passwordError = "Password cannot be empty")
            }
            return
        }
        _loginScreenState.update {
            it.copy(passwordError = "")
        }
    }

}

data class LoginScreenState(
    val userName: String = "",
    val userNameError: String = "",
    val password: String = "",
    val passwordError: String = ""
)


sealed interface LoginScreenEvent {
    data class UserNameEntered(val value: String) : LoginScreenEvent
    data class PasswordEntered(val value: String) : LoginScreenEvent
    object OnLoginClick : LoginScreenEvent
}