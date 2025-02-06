package com.androidace.spendanalyser.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.checkerframework.checker.units.qual.C

@Entity(tableName = "transaction_entity")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("receiver_transaction_name") val receiverTransactionName: String = "",
    @ColumnInfo("amount") val amount: Double = 0.0,
    @ColumnInfo("transaction_type") val transactionType: String = "expense",
    @ColumnInfo("category_id") val categoryId: Int = -1,
    @ColumnInfo("frequency_id") val frequency: Int = -1
)