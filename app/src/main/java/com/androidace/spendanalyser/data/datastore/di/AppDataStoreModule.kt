package com.androidace.spendanalyser.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.androidace.spendanalyser.data.datastore.PreferencesDataStore
import com.androidace.spendanalyser.data.datastore.prefs.IPreferencesDataStore
import com.androidace.spendanalyser.data.datastore.prefs.PreferencesKeys
import com.androidace.spendanalyser.di.qualifiers.Dispatcher
import com.androidace.spendanalyser.di.qualifiers.SADispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataStoreModule {

    @Singleton
    @Provides
    fun providesAppDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            //scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {
                context.preferencesDataStoreFile(PreferencesKeys.PREFERENCES_NAME)//this.applicationContext.filesDir + datastore/ subdirectory
            }
        )
    }


    @Singleton
    @Provides
    fun provideAppDataStorePrefs(dataStore: DataStore<Preferences>): IPreferencesDataStore {
        return PreferencesDataStore(dataStore)
    }

    @Provides
    @Dispatcher(SADispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Dispatcher(SADispatchers.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}