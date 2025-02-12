package com.androidace.spendanalyser.ui.screens.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidace.spendanalyser.ui.components.common.SAScreen
import com.androidace.spendanalyser.ui.components.common.SAState


@Composable
fun PreferencesScreen(onExit: () -> Unit, viewModel: PreferencesViewModel = hiltViewModel()) {
    val saState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    PreferencesScreen(saState = saState)
}

@Composable
fun PreferencesScreen(
    saState: SAState,
    modifier: Modifier = Modifier
) {
    SAScreen(saState = saState, content = {
        Scaffold(topBar = {

        }) {
            Column(modifier = Modifier.padding(it)) {

            }
        }
    })
}