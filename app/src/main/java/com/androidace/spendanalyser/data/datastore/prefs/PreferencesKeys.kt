package com.androidace.spendanalyser.data.datastore.prefs

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val PREFERENCES_NAME = "spend-analyser-preferences"
    val PREF_EXPENSE_FORMAT_KEY = stringPreferencesKey("expense_format")
    val PREF_EXPENSE_CURRENCY_KEY = stringPreferencesKey("expense_currency")
    val PREF_AMOUNT_DECIMAL_SEPARATOR_KEY = stringPreferencesKey("amount_decimal_separator")
    val PREF_AMOUNT_THOUSANDS_SEPARATOR_KEY = stringPreferencesKey("amount_thousands_separator")
    val PREF_SAVE_BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled_prefs")
    val PREF_SAVE_SESSION_EXPIRY_DURATION = stringPreferencesKey("session_expiry_duration")
    val PREF_SAVE_LOCKED_OUT_DURATION = stringPreferencesKey("session_locked_out_duration")
}