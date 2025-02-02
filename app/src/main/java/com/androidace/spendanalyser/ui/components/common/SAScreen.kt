package com.androidace.spendanalyser.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SAScreen(
    saState: SAState,
    loadingScreen: @Composable () -> Unit,
    bottomSheet: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier) {
    when {
        saState.isLoading -> {
            Content(content, bottomSheet)
            loadingScreen()
        }

        else -> {
            Content(content, bottomSheet)
        }
    }
}

@Composable
private fun Content(
    content: @Composable () -> Unit,
    bottomSheet: @Composable() (() -> Unit)
) {
    Box {
        content()
        bottomSheet()
    }
}