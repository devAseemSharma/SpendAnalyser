package com.androidace.spendanalyser.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidace.spendanalyser.R
import com.androidace.spendanalyser.ui.components.buttons.FilledButton
import com.androidace.spendanalyser.ui.components.common.SAScreen
import com.androidace.spendanalyser.ui.components.common.SAState
import com.androidace.spendanalyser.ui.components.textfields.SATextFieldWithBorder


@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val saScreen by viewModel.uiStateHandlerImpl.uiStateFlow.collectAsStateWithLifecycle()
    val loginState by viewModel.loginScreenState.collectAsStateWithLifecycle()

    LoginScreen(
        screenState = saScreen,
        loginState = loginState,
        onLoginClick = { viewModel.onEvent(LoginScreenEvent.OnLoginClick) },
        onRegisterClick = onRegisterClick,
        onUserNameChanged = {
            viewModel.onEvent(LoginScreenEvent.UserNameEntered(it))
        },
        onPasswordChanged = {
            viewModel.onEvent(LoginScreenEvent.PasswordEntered(it))
        }
    )
}

@Composable
fun LoginScreen(
    screenState: SAState,
    loginState: LoginScreenState,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    SAScreen(saState = screenState, content = {
        Scaffold(
            bottomBar = {
                Column(modifier = Modifier.safeContentPadding()) {
                    FilledButton(
                        buttonText = "Login",
                        onClick = onLoginClick,
                        rightIcon = Icons.AutoMirrored.Filled.ArrowForward,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 28.dp)
                    )
                    Text(
                        text = stringResource(R.string.new_to_spendless),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .clickable {
                                onRegisterClick.invoke()
                            }
                    )
                }
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(it)
                    .safeContentPadding()
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_sa_screen),
                    contentDescription = "Icon",
                    modifier = Modifier.padding(top = 36.dp)
                )

                Text(
                    text = stringResource(R.string.welcome_back),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.padding(top = 20.dp)
                )

                Text(
                    text = stringResource(R.string.enter_login_details),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier.padding(top = 8.dp)
                )

                SATextFieldWithBorder(
                    text = loginState.userName,
                    errorText = loginState.userNameError,
                    hintText = "Username",
                    hintTextStyle = MaterialTheme.typography.bodyMedium,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = onUserNameChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp, bottom = 16.dp)
                )

                SATextFieldWithBorder(
                    text = loginState.password,
                    errorText = loginState.passwordError,
                    hintText = "PIN",
                    hintTextStyle = MaterialTheme.typography.bodyMedium,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = onPasswordChanged,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    })

}