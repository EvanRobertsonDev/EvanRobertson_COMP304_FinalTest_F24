package com.example.evanrobertson_comp304_finaltest_f24.data

import kotlinx.coroutines.flow.Flow

class StockRepository(private val dao: StockDAO) {

    //Returns all stocks in db
    val allStocks: Flow<List<StockInfo>> = dao.getAllStocks()

    //Inserts stock into db
    suspend fun insertStock(stockInfo: StockInfo) {
        dao.insert(stockInfo)
    }

    //Removes stock from db
    suspend fun removeStock(stockInfo: StockInfo) {
        dao.delete(stockInfo)
    }

    //Updates existing stock from db
    suspend fun updateStock(stockInfo: StockInfo) {
        dao.update(stockInfo)
    }

    //Gets specific stock from db
    suspend fun getStock(key: String) : StockInfo {
        return dao.getStock(key)
    }
}