package com.example.evanrobertson_comp304_finaltest_f24.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.evanrobertson_comp304_finaltest_f24.data.StockInfo
import com.example.evanrobertson_comp304_finaltest_f24.data.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StockViewModel(
    private val stockRepository: StockRepository
): ViewModel() {

    private val _stockInfo = MutableStateFlow<StockInfo?>(null) // Mutable state
    val stockInfo: StateFlow<StockInfo?> = _stockInfo // Immutable state for the UI

    //Returns all stocks
    fun getStocks() : Flow<List<StockInfo>> {
        return stockRepository.allStocks
    }

    //Inserts stock
    fun insertStock(stockInfo: StockInfo) {
        viewModelScope.launch {
            stockRepository.insertStock(stockInfo)
        }
    }

    //Removes stock
    fun removeStock(stockInfo: StockInfo) {
        viewModelScope.launch {
            stockRepository.removeStock(stockInfo)
        }
    }

    //Updates stock
    fun updateStock(stockInfo: StockInfo) {
        viewModelScope.launch {
            stockRepository.updateStock(stockInfo)
        }
    }

    //Gets stock using symbol
    fun getStock(key: String) {
        viewModelScope.launch {
            val stock = stockRepository.getStock(key)
            _stockInfo.value = stock
        }
    }
}