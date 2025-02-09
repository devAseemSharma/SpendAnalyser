package com.androidace.spendanalyser.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidace.spendanalyser.ui.components.common.ObserveAsEvents
import com.androidace.spendanalyser.ui.components.common.SnackbarController
import com.androidace.spendanalyser.ui.navigation.PinScreen
import com.androidace.spendanalyser.ui.navigation.PromptPinScreen
import com.androidace.spendanalyser.ui.navigation.RegisterScreen
import com.androidace.spendanalyser.ui.screens.auth.PinScreen
import com.androidace.spendanalyser.ui.screens.auth.PromptPinScreen
import com.androidace.spendanalyser.ui.screens.auth.RegistrationScreen
import kotlinx.coroutines.launch

@Composable
fun App(
    viewModel: AppViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    ObserveAsEvents(
        flow = SnackbarController.events,
        snackbarHostState
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Short
            )

            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }
    // Your NavHost or main UI content
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .offset(y = 24.dp)
            ) { data ->
                Snackbar(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = RectangleShape,
                    modifier = Modifier.heightIn(min = 72.dp)
                ) {
                    Text(
                        data.visuals.message,
                        style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        NavHost(
            navController = navController, startDestination = RegisterScreen,
        ) {
            composable<RegisterScreen> {
                RegistrationScreen(onExit = {
                    navController.navigate(PinScreen)
                })
            }
            composable<PinScreen> {
                PinScreen(onExit = {})
            }

            composable<PromptPinScreen> {
                PromptPinScreen(onExit = {})
            }
        }
    }

}