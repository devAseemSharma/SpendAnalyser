package com.androidace.spendanalyser.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidace.spendanalyser.R
import com.androidace.spendanalyser.ui.components.common.SAScreen
import com.androidace.spendanalyser.ui.components.common.SAState
import com.androidace.spendanalyser.ui.components.common.SnackbarController
import com.androidace.spendanalyser.ui.components.common.SnackbarEvent
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
            onExit.invoke()
        }
    )
}


@Composable
fun RegistrationScreen(
    uiState: SAState,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {

    SAScreen(
        saState = uiState,
        loadingScreen = {

        },
        bottomSheet = {

        },
        content = {
            Scaffold {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {

                    Image(
                        painter = painterResource(R.drawable.ic_sa_screen),
                        contentDescription = "Icon"
                    )

                    Button(onClick = onExit) {
                        Text("Register")
                    }
                }
            }
        },
    )
}