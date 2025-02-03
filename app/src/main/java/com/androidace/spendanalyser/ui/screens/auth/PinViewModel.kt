package com.androidace.spendanalyser.ui.screens.auth

import androidx.lifecycle.ViewModel
import com.androidace.spendanalyser.ui.components.common.UIStateHandlerImpl
import com.androidace.spendanalyser.ui.components.common.UiStateHandler
import com.androidace.spendanalyser.ui.components.pin.PinKeyPadListenerImpl
import com.androidace.spendanalyser.ui.components.pin.model.KeyPadEntry
import com.androidace.spendanalyser.ui.components.pin.model.PinCellModel
import com.androidace.spendanalyser.ui.components.pin.model.PinCellStatus
import com.androidace.spendanalyser.ui.components.pin.model.PinState
import com.androidace.spendanalyser.ui.components.pin.model.PinVerifyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(val uiStateHandlerImpl: UIStateHandlerImpl,
                                       private val keyPadListener: PinKeyPadListenerImpl,) : ViewModel(),
    UiStateHandler by uiStateHandlerImpl {

    private var _cashPinInputList =
        MutableStateFlow<PersistentList<PinCellModel>>(persistentListOf())
    val cashPinInputList = _cashPinInputList.asStateFlow()

    private var _timerState = MutableStateFlow("00:00")
    val timerState = _timerState.asStateFlow()

    private var _cashOutPinValues = MutableStateFlow("")
    val cashOutPinValues = _cashOutPinValues.asStateFlow()

    private var pinAttempts = 3

    private var _pinState = MutableStateFlow(
        PinState(
            remainingAttempts = pinAttempts,
            numberOfAttempts = 0,
            pinStatus = PinVerifyState.UN_VERIFIED,
            maxAttempt = null
        )
    )
    val pinState = _pinState.asStateFlow()

    init {
        createPinInputCells()
    }

    private fun createPinInputCells() {
        val list = ArrayList<PinCellModel>()
        repeat(4) {
            val cellModel = PinCellModel(
                data = "",
                maskedKey = "*",
            )
            if (it == 0) {
                cellModel.setCellFocus(PinCellStatus.FOCUSSED)
            }
            list.add(cellModel)
        }
        _cashPinInputList.value = list.toPersistentList()
    }

    fun onPinValueChange(value: KeyPadEntry) {
        if ((value == KeyPadEntry.BackSpace || value is KeyPadEntry.Char)
            && _pinState.value.pinStatus != PinVerifyState.UN_VERIFIED
        ) {
            // We need to clear pin in case of backpress of pin after success or failure, mostly in failure
            resetPin()
        }
        keyPadListener.onKeypadUpdate(
            _cashOutPinValues.value,
            value,
            onNextFocus = {
                _cashOutPinValues.value = it
                val cellModel = PinCellModel(
                    data = if (it.isNotEmpty()) it.last().toString() else "",
                    maskedKey = "*",
                )
                _cashPinInputList.value = _cashPinInputList.value.set((it.length - 1), cellModel)
            },
            onPreviousFocus = {
                _cashOutPinValues.value = it
                val cellModel = PinCellModel(
                    data = "",
                    maskedKey = "*"
                )
                val backIndex = if (it.isNotEmpty()) it.length else 0
                _cashPinInputList.value = _cashPinInputList.value.set(backIndex, cellModel)
            })
    }

    private fun resetPin() {
        val cellModel = PinCellModel(
            data = "",
            maskedKey = "*",
        )
        _cashPinInputList.value = _cashPinInputList.value.map {
            cellModel
        }.toPersistentList()
        _cashOutPinValues.value = ""
        _pinState.update {
            it.copy(pinStatus = PinVerifyState.UN_VERIFIED)
        }
    }

}