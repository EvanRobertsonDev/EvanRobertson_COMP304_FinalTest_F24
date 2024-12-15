package com.example.evanrobertson_comp304_finaltest_f24


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.evanrobertson_comp304_finaltest_f24.view.StockView

class ViewStockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                //Get passed symbol
                intent.getStringExtra("stock")?.let {
                    StockView(
                        symbol = it,
                        onBack = {
                            //Change activity
                            finish()
                        }
                    )
                }
            }
        }
    }
