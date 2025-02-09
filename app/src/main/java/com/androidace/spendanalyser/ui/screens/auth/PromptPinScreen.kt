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
import com.androidace.spendanalyser.ui.navigation.PromptPinScreen
import kotlinx.collections.immutable.PersistentList

@Composable
fun PromptPinScreen(
    onExit: () -> Unit,
    pinViewModel: PinViewModel = hiltViewModel()
) {
    val uiState by pinViewModel.uiStateHandlerImpl.uiStateFlow.collectAsStateWithLifecycle()
    val pinValues by pinViewModel.cashOutPinValues.collectAsStateWithLifecycle()
    val inputPinCells by pinViewModel.cashPinInputList.collectAsStateWithLifecycle()
    val timerState by pinViewModel.timerState.collectAsStateWithLifecycle()
    val pinState by pinViewModel.pinState.collectAsStateWithLifecycle()


    PromptPinScreen(uiState = uiState,
        pinCellList = inputPinCells,
        pinState = pinState,
        timerState = timerState,
        onPinContinue = {},
        onValueChange = {},
        resetTimer = {})
}


@Composable
fun PromptPinScreen(
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