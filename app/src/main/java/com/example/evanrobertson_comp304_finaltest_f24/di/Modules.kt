package com.example.evanrobertson_comp304_finaltest_f24.di

import androidx.room.Room.databaseBuilder
import com.example.evanrobertson_comp304_finaltest_f24.data.StockDatabase
import com.example.evanrobertson_comp304_finaltest_f24.data.StockRepository
import com.example.evanrobertson_comp304_finaltest_f24.view.StockView
import com.example.evanrobertson_comp304_finaltest_f24.viewmodel.StockViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val json = Json

val appModules = module {
    single<StockRepository> { StockRepository(get()) }
    single { Dispatchers.IO }
    single {
        databaseBuilder(
            androidContext(),
            StockDatabase::class.java,
            "stock-database"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<StockDatabase>().stockDao() }

    viewModel { StockViewModel(get()) }
}