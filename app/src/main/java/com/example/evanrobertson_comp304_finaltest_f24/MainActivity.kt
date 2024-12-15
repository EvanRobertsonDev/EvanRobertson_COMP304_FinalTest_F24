package com.example.evanrobertson_comp304_finaltest_f24

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.evanrobertson_comp304_finaltest_f24.di.appModules
import com.example.evanrobertson_comp304_finaltest_f24.view.MainView
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(applicationContext)
            modules(appModules)
        }

        setContent {
            MainView(
                //Pass symbol to get stock
                onStockClick = { symbol ->
                    val intent = Intent(this, ViewStockActivity::class.java).apply {
                        putExtra("stock", symbol)
                    }
                    //Change activity
                    startActivity(intent)
                }
            )
        }
    }
}