package com.androidace.spendanalyser.ui.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.androidace.spendanalyser.ui.components.common.SAScreen

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel = hiltViewModel(), onExit: () -> Unit) {

    RegistrationScreen(modifier = Modifier)
}


@Composable
fun RegistrationScreen(modifier: Modifier = Modifier) {
    SAScreen()
}