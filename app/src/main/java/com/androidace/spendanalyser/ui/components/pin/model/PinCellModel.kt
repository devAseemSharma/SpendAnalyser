package com.androidace.spendanalyser.ui.components.pin.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class PinCellModel(
    val data: String,
    val maskedKey: String = "*",
    private val cellFocus: CellSelectable = PinCellSelectableImpl()
) : CellSelectable by cellFocus


enum class PinCellStatus {
    FOCUSSED,
    UNFOCUSED
}

interface CellSelectable {
    val cellSelectable: PinCellStatus
    fun setCellFocus(cellFocus: PinCellStatus)
}

class PinCellSelectableImpl(
    private val mutableCellSelectable: MutableState<PinCellStatus> = mutableStateOf(PinCellStatus.UNFOCUSED)
) : CellSelectable {
    override val cellSelectable: PinCellStatus
        get() = mutableCellSelectable.value

    override fun setCellFocus(cellFocus: PinCellStatus) {
        mutableCellSelectable.value = cellFocus
    }

}