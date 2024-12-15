package com.example.evanrobertson_comp304_finaltest_f24.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evanrobertson_comp304_finaltest_f24.data.StockInfo
import com.example.evanrobertson_comp304_finaltest_f24.data.StockRepository
import com.example.evanrobertson_comp304_finaltest_f24.viewmodel.StockViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StockView(symbol: String, onBack: () -> Unit, stockViewModel: StockViewModel = koinViewModel()) {
    val stock by stockViewModel.stockInfo.collectAsState()

    LaunchedEffect(symbol) {
        //Get stock using symbol
        stockViewModel.getStock(symbol)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            //Page title
            text = "Stock Information",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        //Symbol Text
        Text("Symbol: ${stock?.stockSymbol}", style = MaterialTheme.typography.bodyLarge)
        //Company Text
        Text("Company: ${stock?.companyName}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        //Large quote value
        Text("$${stock?.stockQuote}", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(16.dp))

        //Remove stock from db
        Button(onClick = {
            stockViewModel.removeStock(stock!!)
            onBack()
        }) {
            Text("Remove")
        }
        Spacer(modifier = Modifier.width(16.dp))

        //Return to main
        Button(onClick = { onBack() }) {
            Text("Back")
        }
    }
}