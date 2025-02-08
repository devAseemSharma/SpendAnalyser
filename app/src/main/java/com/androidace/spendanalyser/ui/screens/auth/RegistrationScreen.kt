package com.androidace.spendanalyser.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.androidace.spendanalyser.ui.components.common.SnackbarController
import com.androidace.spendanalyser.ui.components.common.SnackbarEvent
import com.androidace.spendanalyser.ui.components.textfields.SATextField
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel = hiltViewModel(), onExit: () -> Unit) {
    val uiState by viewModel.uiStateImpl.uiStateFlow.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    RegistrationScreen(
        uiState = uiState,
        onExit = {
            scope.launch {
                SnackbarController.sendEvent(
                    SnackbarEvent(
                        message = "Successful Registration!",
                        null
                    )
                )
            }
        },
        onLoginRedirect = {
            onExit.invoke()
        }
    )
}


@Composable
fun RegistrationScreen(
    uiState: SAState,
    onExit: () -> Unit,
    onLoginRedirect: () -> Unit,
    modifier: Modifier = Modifier
) {

    SAScreen(
        saState = uiState,
        loadingScreen = {

        },
        bottomSheet = {

        },
        content = {
            Scaffold(
                bottomBar = {
                    Column(modifier = Modifier.safeContentPadding()) {
                        FilledButton(
                            buttonText = "Next",
                            onClick = onExit,
                            rightIcon = Icons.AutoMirrored.Filled.ArrowForward,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 28.dp)
                        )
                        Text(
                            text = stringResource(R.string.already_account),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .clickable {
                                    onLoginRedirect.invoke()
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
                        text = stringResource(R.string.welcome_text),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Text(
                        text = stringResource(R.string.unique_username),
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    SATextField()
                }
            }
        },
    )
}