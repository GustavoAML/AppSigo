package com.example.appsigo.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import com.example.appsigo.SigoLoginApplication

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CompositionLocalProvider(LocalApp provides application as SigoLoginApplication) {
                    WelcomeScreen(onNavigateMain = { intent ->
                        startActivity(intent)
                        finish()
                    })
                }
            }
        }
    }
}