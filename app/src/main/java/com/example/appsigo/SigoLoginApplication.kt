package com.example.appsigo

import android.app.Application
import com.example.appsigo.di.AppContainer

class SigoLoginApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}