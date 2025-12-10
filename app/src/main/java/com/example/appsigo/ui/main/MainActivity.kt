package com.example.appsigo.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appsigo.SigoLoginApplication
import com.example.appsigo.ui.historial.HistorialScreen
import com.example.appsigo.ui.login.LocalApp
import com.example.appsigo.ui.perfil.PerfilScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CompositionLocalProvider(LocalApp provides application as SigoLoginApplication) {
                    var currentScreen by remember { mutableStateOf("principal") }

                    Scaffold(
                        topBar = { TopAppBar(title = { Text("PÁGINA PRINCIPAL") }) }
                    ) { padding ->
                        Column(
                            Modifier
                                .padding(padding)
                                .padding(16.dp)
                        ) {
                            Text("ID: UTM214041BT1", style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(16.dp))

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Button(onClick = { currentScreen = "perfil" }) { Text("PERFIL") }
                                Button(onClick = { currentScreen = "historial" }) { Text("HISTORIAL") }
                            }

                            Spacer(Modifier.height(24.dp))

                            when (currentScreen) {
                                "perfil" -> PerfilScreen()
                                "historial" -> HistorialScreen(activity = this@MainActivity)
                                else -> {
                                    Text("AVISOS:", style = MaterialTheme.typography.titleMedium)
                                    Text("La universidad es un espacio donde cada día se mezclan ideas...")
                                    Spacer(Modifier.height(8.dp))
                                    Text("Los estudiantes descubren nuevas formas de pensar...")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}