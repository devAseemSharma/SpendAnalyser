package com.androidace.spendanalyser.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.androidace.spendanalyser.data.datastore.prefs.IPreferencesDataStore
import com.androidace.spendanalyser.data.datastore.prefs.PreferencesKeys.PREF_AMOUNT_DECIMAL_SEPARATOR_KEY
import com.androidace.spendanalyser.data.datastore.prefs.PreferencesKeys.PREF_AMOUNT_THOUSANDS_SEPARATOR_KEY
import com.androidace.spendanalyser.data.datastore.prefs.PreferencesKeys.PREF_EXPENSE_FORMAT_KEY
import com.androidace.spendanalyser.data.datastore.prefs.PreferencesKeys.PREF_SAVE_BIOMETRIC_ENABLED
import com.androidace.spendanalyser.data.datastore.prefs.PreferencesKeys.PREF_SAVE_LOCKED_OUT_DURATION
import com.androidace.spendanalyser.data.datastore.prefs.PreferencesKeys.PREF_SAVE_SESSION_EXPIRY_DURATION
import com.androidace.spendanalyser.di.qualifiers.Dispatcher
import com.androidace.spendanalyser.di.qualifiers.SADispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(
    private val prefsDataStore: DataStore<Preferences>,
    @Dispatcher(SADispatchers.IO) val dispatcher: CoroutineDispatcher
) : IPreferencesDataStore {
    override suspend fun saveExpenseFormat(format: String) {
        prefsDataStore.edit {
            it[PREF_EXPENSE_FORMAT_KEY] = format
        }
    }

    override suspend fun saveExpenseCurrency(currency: String) {
        prefsDataStore.edit {
            it[PREF_EXPENSE_FORMAT_KEY] = currency
        }
    }

    override suspend fun saveAmountDecimalSeparator(separator: String) {
        prefsDataStore.edit {
            it[PREF_AMOUNT_DECIMAL_SEPARATOR_KEY] = separator
        }
    }

    override suspend fun saveThousandSeparator(separator: String) {
        prefsDataStore.edit {
            it[PREF_AMOUNT_THOUSANDS_SEPARATOR_KEY] = separator
        }
    }

    override suspend fun saveBioMetricPinPrompt(isBioMetricEnabled: Boolean) {
        prefsDataStore.edit {
            it[PREF_SAVE_BIOMETRIC_ENABLED] = isBioMetricEnabled
        }
    }

    override suspend fun saveSessionExpiryDuration(duration: String) {
        prefsDataStore.edit {
            it[PREF_SAVE_SESSION_EXPIRY_DURATION] = duration
        }
    }

    override suspend fun saveLockedOutDuration(duration: String) {
        prefsDataStore.edit {
            it[PREF_SAVE_LOCKED_OUT_DURATION] = duration
        }
    }

    override suspend fun getExpenseFormat(): String? {
        return withContext(dispatcher) {
            prefsDataStore.data.map {
                it[PREF_EXPENSE_FORMAT_KEY]
            }.firstOrNull()
        }
    }

}