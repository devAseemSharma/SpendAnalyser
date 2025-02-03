package com.androidace.spendanalyser.ui.screens.auth

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidace.spendanalyser.R
import com.androidace.spendanalyser.ui.components.common.SAScreen
import com.androidace.spendanalyser.ui.components.common.SAState
import com.androidace.spendanalyser.ui.components.pin.PinLayout
import com.androidace.spendanalyser.ui.components.pin.model.KeyPadEntry
import com.androidace.spendanalyser.ui.components.pin.model.PinCellModel
import com.androidace.spendanalyser.ui.components.pin.model.PinState
import kotlinx.collections.immutable.PersistentList


@Composable
fun PinScreen(
    viewModel: PinViewModel = hiltViewModel(),
    onExit: () -> Unit
) {
    val uiState by viewModel.uiStateHandlerImpl.uiStateFlow.collectAsStateWithLifecycle()
    val pinValues by viewModel.cashOutPinValues.collectAsStateWithLifecycle()
    val inputPinCells by viewModel.cashPinInputList.collectAsStateWithLifecycle()
    val timerState by viewModel.timerState.collectAsStateWithLifecycle()
    val pinState by viewModel.pinState.collectAsStateWithLifecycle()
    PinScreen(
        uiState = uiState,
        pinCellList = inputPinCells,
        pinState = pinState,
        timerState = timerState,
        resetTimer = {},
        onValueChange = viewModel::onPinValueChange,
        onPinContinue = {}
    )
}


@Composable
fun PinScreen(
    uiState: SAState,
    pinCellList: PersistentList<PinCellModel>,
    pinState: PinState,
    timerState: String,
    onPinContinue: () -> Unit,
    onValueChange: (KeyPadEntry) -> Unit,
    resetTimer: () -> Unit,
    modifier: Modifier = Modifier
) {

    SAScreen(
        saState = uiState,
        loadingScreen = {},
        bottomSheet = {},
        content = {
            Scaffold {
                PinLayout(
                    it = it,
                    timer = timerState,
                    otpPinState = pinState,
                    sectionTitle = R.string.hg_resource_pin_section_title,
                    pinCellList = pinCellList,
                    onValueChange = onValueChange,
                    resetTimer = resetTimer,
                    deleteImage = R.drawable.ic_delete_pin
                )
            }
        },
    )

}