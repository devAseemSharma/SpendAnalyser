package com.androidace.spendanalyser.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidace.spendanalyser.data.db.dao.TransactionDao
import com.androidace.spendanalyser.data.db.entity.TransactionEntity


@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}