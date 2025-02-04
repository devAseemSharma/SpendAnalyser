package com.androidace.spendanalyser.ui.screens.settings

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.androidace.spendanalyser.ui.components.common.SAScreen
import com.androidace.spendanalyser.ui.components.common.SAState

@Composable
fun SettingScreen(onExit: () -> Unit) {

}

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    uiState: SAState
) {

    SAScreen(saState = uiState, loadingScreen = {}, bottomSheet = {}, content = {
        Scaffold {

        }
    })

}