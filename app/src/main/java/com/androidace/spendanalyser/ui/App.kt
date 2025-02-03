package com.androidace.spendanalyser.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidace.spendanalyser.ui.navigation.PinScreen
import com.androidace.spendanalyser.ui.navigation.RegisterScreen
import com.androidace.spendanalyser.ui.screens.auth.PinScreen
import com.androidace.spendanalyser.ui.screens.auth.RegistrationScreen

@Composable
fun App(
    viewModel: AppViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    // Your NavHost or main UI content
    NavHost(
        navController = navController, startDestination = RegisterScreen
    ) {
        composable<RegisterScreen> {
            RegistrationScreen(onExit = {
                navController.navigate(PinScreen)
            })
        }
        composable<PinScreen> {
            PinScreen(onExit = {})
        }
    }
}