package com.example.appsigo.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appsigo.SigoLoginApplication
import com.example.appsigo.ui.historial.HistorialScreen
import com.example.appsigo.ui.login.LocalApp
import com.example.appsigo.ui.login.LoginActivity
import com.example.appsigo.ui.perfil.PerfilScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CompositionLocalProvider(LocalApp provides application as SigoLoginApplication) {
                    var currentScreen by remember { mutableStateOf("principal") }
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                Spacer(Modifier.height(24.dp))
                                NavigationDrawerItem(
                                    label = { Text("PRINCIPAL") },
                                    selected = currentScreen == "principal",
                                    onClick = {
                                        currentScreen = "principal"
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                NavigationDrawerItem(
                                    label = { Text("PERFIL") },
                                    selected = currentScreen == "perfil",
                                    onClick = {
                                        currentScreen = "perfil"
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                NavigationDrawerItem(
                                    label = { Text("HISTORIAL") },
                                    selected = currentScreen == "historial",
                                    onClick = {
                                        currentScreen = "historial"
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                Spacer(Modifier.height(24.dp))
                                NavigationDrawerItem(
                                    label = { Text("CERRAR SESIÓN") },
                                    selected = false,
                                    onClick = {
                                        scope.launch { drawerState.close() }
                                        val intent = Intent(this@MainActivity, LoginActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                )
                            }
                        }
                    ) {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = { Text("PÁGINA PRINCIPAL") },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            scope.launch { drawerState.open() }
                                        }) {
                                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                                        }
                                    }
                                )
                            }
                        ) { padding ->
                            Column(
                                Modifier
                                    .padding(padding)
                                    .padding(16.dp)
                            ) {
                                // Matricula + icono juntos en el lado derecho
                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Spacer(Modifier.width(1.dp)) // lado izquierdo vacío
                                    Row {
                                        Text("UTM214041BT1", style = MaterialTheme.typography.titleMedium)
                                        Spacer(Modifier.width(8.dp))
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "Perfil",
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                }

                                Spacer(Modifier.height(24.dp))

                                when (currentScreen) {
                                    "perfil" -> PerfilScreen()
                                    "historial" -> HistorialScreen(activity = this@MainActivity)
                                    else -> {
                                        Text("AVISOS:", style = MaterialTheme.typography.titleMedium)
                                        Spacer(Modifier.height(8.dp))
                                        Text("La universidad es un espacio donde cada día se mezclan ideas, proyectos y personas con metas muy distintas pero un mismo propósito: crecer.")

                                        Spacer(Modifier.height(16.dp))

                                        Icon(
                                            imageVector = Icons.Default.Info,
                                            contentDescription = "Convocatoria",
                                            modifier = Modifier.size(48.dp)
                                        )
                                        Text("CONVOCATORIA ABIERTA\nDel 13 de febrero al 13 de abril\nRegistro en: www.becas-santander.com")

                                        Spacer(Modifier.height(16.dp))

                                        Icon(
                                            imageVector = Icons.Default.Info,
                                            contentDescription = "Inspiración",
                                            modifier = Modifier.size(48.dp)
                                        )
                                        Text("Los estudiantes descubren nuevas formas de pensar gracias a profesores que no solo comparten conocimientos, sino experiencias reales que inspiran.")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}