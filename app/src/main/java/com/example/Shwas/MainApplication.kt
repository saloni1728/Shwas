package com.example.Shwas

import android.app.Application
import com.example.Shwas.data.dataPersistence.DatabaseInitializer
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MainApplication: Application() {
    @Inject
    lateinit var databaseInitializer: DatabaseInitializer

    private val applicationScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            databaseInitializer.initializeDatabase()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        applicationScope.launch {
            databaseInitializer.clearDatabase()
        }
    }
}