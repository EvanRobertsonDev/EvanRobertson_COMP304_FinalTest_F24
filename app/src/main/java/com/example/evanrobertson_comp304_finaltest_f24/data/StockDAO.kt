package com.example.evanrobertson_comp304_finaltest_f24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDAO {
    //Inserts stock info into db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stockInfo: StockInfo)

    //Gets all stocks from db
    @Query("SELECT * FROM Stocks")
    fun getAllStocks(): Flow<List<StockInfo>>

    //Deletes stock from db
    @Delete
    suspend fun delete(stockInfo: StockInfo)

    //Update stock in db
    @Update
    suspend fun update(stockInfo: StockInfo)

    //Gets specific stock from db
    @Query("SELECT * FROM Stocks WHERE stockSymbol = :stockSymbol")
    suspend fun getStock(stockSymbol: String): StockInfo

}