package com.androidace.spendanalyser.ui.components.pin

import com.androidace.spendanalyser.ui.components.pin.model.KeyPadEntry
import javax.inject.Inject

fun interface PinKeyPadListener {
    fun onKeypadUpdate(
        pin: String,
        keypadEntry: KeyPadEntry,
        onNextFocus: (String) -> Unit,
        onPreviousFocus: (String) -> Unit
    ): Unit
}

class PinKeyPadListenerImpl @Inject constructor() : PinKeyPadListener {
    override fun onKeypadUpdate(
        pin: String,
        keypadEntry: KeyPadEntry,
        onNextFocus: (String) -> Unit,
        onPreviousFocus: (String) -> Unit
    ) {
        if (pin.length == 4 && keypadEntry is KeyPadEntry.Char) return
        return pin.updateKeyPadEntry(keypadEntry, onNextFocus, onPreviousFocus)
    }

    private fun String.removeLastCharacter() = if (this.isNotBlank()) {
        this.dropLast(1)
    } else {
        ""
    }

    private fun String.appendIfValid(value: String): String {
        if (value.length == 4) {
            return this
        }
        return this.plus(value)
    }

    private fun String.updateKeyPadEntry(
        keyPadEntry: KeyPadEntry,
        onNextFocus: (String) -> Unit,
        onPreviousFocus: (String) -> Unit
    ) {
        when (keyPadEntry) {
            KeyPadEntry.BackSpace -> {
                onPreviousFocus(this.removeLastCharacter())
            }

            is KeyPadEntry.Char -> {
                onNextFocus(this.appendIfValid(keyPadEntry.value))
            }

            KeyPadEntry.EmptyKey -> {
                this
            }
        }
    }
}