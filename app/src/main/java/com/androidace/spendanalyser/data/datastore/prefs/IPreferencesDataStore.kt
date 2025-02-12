package com.androidace.spendanalyser.data.datastore.prefs

interface IPreferencesDataStore {
    suspend fun saveExpenseFormat(format: String)
    suspend fun saveExpenseCurrency(currency: String)
    suspend fun saveAmountDecimalSeparator(separator: String)
    suspend fun saveThousandSeparator(separator: String)
    suspend fun saveBioMetricPinPrompt(isBioMetricEnabled: Boolean)
    suspend fun saveSessionExpiryDuration(duration: String)
    suspend fun saveLockedOutDuration(duration: String)
    suspend fun getExpenseFormat():String?
}