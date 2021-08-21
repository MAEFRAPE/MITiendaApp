package com.example.myapplication

import android.app.Application
import com.example.myapplication.Di.dataSourceModule
import com.example.myapplication.Di.databaseModule
import com.example.myapplication.Di.repoModule
import com.example.myapplication.Di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataSourceModule, repoModule, viewmodelModule, databaseModule))
        }
    }
}