package com.example.appsigo.ui.login

import android.app.Application
import androidx.compose.runtime.staticCompositionLocalOf

val LocalApp = staticCompositionLocalOf<Application> {
    error("Application no disponible")
}
