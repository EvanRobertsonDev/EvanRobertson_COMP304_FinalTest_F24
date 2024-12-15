package com.example.evanrobertson_comp304_finaltest_f24.view

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import com.example.evanrobertson_comp304_finaltest_f24.data.StockInfo
import com.example.evanrobertson_comp304_finaltest_f24.viewmodel.StockViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainView(
    onStockClick: (String) -> Unit,
    stockViewModel: StockViewModel = koinViewModel()
) {
    val stockList by stockViewModel.getStocks().collectAsState(emptyList())
    var symbol by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var quote by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        //Symbol field
        TextField(
            value = symbol,
            onValueChange = { symbol = it },
            label = { Text("Stock Symbol") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        //Company field
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Company Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        //Quote field
        TextField(
            value = quote,
            onValueChange = { quote = it },
            label = { Text("Stock Quote") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                //Check all values are valid
                if (symbol.isNotBlank() && name.isNotBlank() && quote.isNotBlank() && quote.toDoubleOrNull() != null) {

                    //Create new stock
                    val newStock = StockInfo(symbol, name, quote.toDouble())

                    //Check if stock already exists
                    if (stockList.contains(newStock)) {
                        //Update existing stock in db
                        stockViewModel.updateStock(newStock)
                    }
                    else {
                        //Add new stock to db
                        stockViewModel.insertStock(newStock)
                    }

                    //Reset fields
                    symbol = ""
                    name = ""
                    quote = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Stock")
        }
        Spacer(modifier = Modifier.height(16.dp))
        //Display all stocks in db
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(stockList) { stock ->
                StockItem(stock = stock, onClick = { onStockClick(stock.stockSymbol) })
            }
        }
    }
}

@Composable
fun StockItem(stock: StockInfo, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() }
    ) {
        //Show stock symbol and company
        Text(
            text = "${stock.stockSymbol} - ${stock.companyName}",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        //Show quote
        Text(
            text = "$${stock.stockQuote}",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}