package com.example.evanrobertson_comp304_finaltest_f24.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//Stocks database
@Database(
    entities = [StockInfo::class],
    version = 1
)
@TypeConverters(StockTypeConverters::class)
abstract class StockDatabase: RoomDatabase() {
    abstract fun stockDao(): StockDAO
}