package com.androidace.spendanalyser.ui.components.pin.model

sealed interface KeyPadEntry {
    class Char(val value: String) : KeyPadEntry
    data object BackSpace : KeyPadEntry
    data object EmptyKey : KeyPadEntry
}

val keypadKeys: List<KeyPadEntry> = listOf(
    KeyPadEntry.Char("1"),
    KeyPadEntry.Char("4"),
    KeyPadEntry.Char("7"),
    KeyPadEntry.Char("."),
    KeyPadEntry.Char("2"),
    KeyPadEntry.Char("5"),
    KeyPadEntry.Char("8"),
    KeyPadEntry.Char("0"),
    KeyPadEntry.Char("3"),
    KeyPadEntry.Char("6"),
    KeyPadEntry.Char("9"),
    KeyPadEntry.BackSpace
)

val pinKeypadKeys: List<KeyPadEntry> = listOf(
    KeyPadEntry.Char("1"),
    KeyPadEntry.Char("4"),
    KeyPadEntry.Char("7"),
    KeyPadEntry.EmptyKey,
    KeyPadEntry.Char("2"),
    KeyPadEntry.Char("5"),
    KeyPadEntry.Char("8"),
    KeyPadEntry.Char("0"),
    KeyPadEntry.Char("3"),
    KeyPadEntry.Char("6"),
    KeyPadEntry.Char("9"),
    KeyPadEntry.BackSpace
)