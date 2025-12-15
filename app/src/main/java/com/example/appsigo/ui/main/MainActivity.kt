package com.example.appsigo.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appsigo.R
import com.example.appsigo.SigoLoginApplication
import com.example.appsigo.theme.primaryLight
import com.example.appsigo.ui.historial.HistorialScreen
import com.example.appsigo.ui.login.LocalApp
import com.example.appsigo.ui.login.LoginActivity
import com.example.appsigo.ui.perfil.PerfilScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = (application as SigoLoginApplication).appContainer.usuarioActual

        setContent {
            MaterialTheme() {
                CompositionLocalProvider(LocalApp provides application as SigoLoginApplication) {
                    var currentScreen by remember { mutableStateOf("principal") }
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                Spacer(Modifier.height(16.dp))
                                NavigationDrawerItem(
                                    label = { Text("PRINCIPAL") },
                                    selected = currentScreen == "principal",
                                    onClick = {
                                        currentScreen = "principal"
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                Divider(modifier = Modifier.padding(vertical = 8.dp))
                                NavigationDrawerItem(
                                    label = { Text("PERFIL") },
                                    selected = currentScreen == "perfil",
                                    onClick = {
                                        currentScreen = "perfil"
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                Divider(modifier = Modifier.padding(vertical = 8.dp))
                                NavigationDrawerItem(
                                    label = { Text("HISTORIAL ACADÉMICO") },
                                    selected = currentScreen == "historial",
                                    onClick = {
                                        currentScreen = "historial"
                                        scope.launch { drawerState.close() }
                                    }
                                )
                                Divider(modifier = Modifier.padding(vertical = 8.dp))
                                Spacer(Modifier.height(530.dp))
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
                            containerColor = MaterialTheme.colorScheme.background,
                            topBar = {
                                TopAppBar(
                                    title = {
                                        if (currentScreen == "principal") {
                                            Text("PÁGINA PRINCIPAL")
                                        }
                                    },
                                    navigationIcon = {
                                        IconButton(onClick = {
                                            scope.launch { drawerState.open() }
                                        }) {
                                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                                        }
                                    }
                                )
                            }
                        )
                        { padding ->
                            Column(
                                Modifier
                                    .padding(padding)
                                    .padding(16.dp)
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                Divider(modifier = Modifier.padding(vertical = 1.dp))

                                // Matricula + icono juntos en el lado derecho
                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Spacer(Modifier.width(1.dp)) // lado izquierdo vacío
                                    Row {
                                        Text("${user?.username}", style = MaterialTheme.typography.titleMedium)
                                        Spacer(Modifier.width(6.dp))
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "Perfil",
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                }

                                Spacer(Modifier.height(4.dp))

                                when (currentScreen) {
                                    "perfil" -> PerfilScreen(user = user!!)
                                    "historial" -> HistorialScreen(activity = this@MainActivity)
                                    else -> {
                                        Text("AVISOS:", style = MaterialTheme.typography.titleLarge,
                                            color = primaryLight,
                                        )
                                        Spacer(Modifier.height(8.dp))
                                        Card(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.surface
                                            ),
                                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                            shape = RoundedCornerShape(12.dp)) {
                                            Text("La universidad es un espacio donde cada día se mezclan ideas, " +
                                                    "proyectos y personas con metas muy distintas pero un mismo propósito: crecer.")

                                        }

                                        Spacer(Modifier.height(8.dp))

                                        Image(
                                            painter = painterResource(id = R.drawable.aviso),
                                            contentDescription = "Aviso1",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(100.dp)
                                        )

                                        Card(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.surface
                                            ),
                                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                            shape = RoundedCornerShape(8.dp)) {
                                            Text("CONVOCATORIA ABIERTA\nDel 13 de febrero al 13 de abril\nRegistro en: www.becas-santander.com")

                                        }

                                        Spacer(Modifier.height(8.dp))

                                            Column(modifier = Modifier.padding(8.dp),
                                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                                horizontalAlignment = Alignment.Start) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.aviso2),
                                                    contentDescription = "Aviso",
                                                    modifier = Modifier
                                                        .size(180.dp)
                                                )
                                            }

                                        Card(modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.surface
                                            ),
                                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                            shape = RoundedCornerShape(8.dp)) {
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
}