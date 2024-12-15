package com.example.evanrobertson_comp304_finaltest_f24.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Stocks")
data class StockInfo(
    @PrimaryKey val stockSymbol: String = "",
    val companyName: String ="",
    val stockQuote: Double = 0.0
)

