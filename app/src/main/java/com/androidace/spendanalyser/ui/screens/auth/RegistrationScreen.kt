package com.androidace.spendanalyser.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidace.spendanalyser.R
import com.androidace.spendanalyser.ui.components.common.SAScreen
import com.androidace.spendanalyser.ui.components.common.SAState

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel = hiltViewModel(), onExit: () -> Unit) {
    val uiState by viewModel.uiStateImpl.uiStateFlow.collectAsStateWithLifecycle()
    RegistrationScreen(
        uiState = uiState
    )
}


@Composable
fun RegistrationScreen(
    uiState: SAState,
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
                }
            }
        },
    )
}