package com.androidace.spendanalyser.di

import android.content.Context
import androidx.room.Room
import com.androidace.spendanalyser.crypto.AppDatabasePassphrase
import com.androidace.spendanalyser.data.db.AppDatabase
import com.androidace.spendanalyser.data.db.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserDatabasePassphrase(@ApplicationContext context: Context) =
        AppDatabasePassphrase(context)

    @Provides
    @Singleton
    fun provideSupportFactory(userDatabasePassphrase: AppDatabasePassphrase) =
        SupportFactory(userDatabasePassphrase.getPassphrase())

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        supportFactory: SupportFactory
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "sa_app_database")
            .openHelperFactory(supportFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(db: AppDatabase): TransactionDao {
        return db.transactionDao()
    }
}