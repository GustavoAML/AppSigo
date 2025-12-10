package com.example.appsigo.ui.historial

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun HistorialScreen(activity: ComponentActivity) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text("HISTORIAL ACADÉMICO", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text("4TO CUATRIMESTRE (SEP-DIC 2025)")
                Text("ACTIVO")
                Text("CARRERA: DESARROLLO DE SOFTWARE")
                Text("GRUPO: 4A MATUTINO")
                Text("TUTOR: DRA. GRICELDA RODRIGUEZ R...")
                Text("PROGRESO: 49%")
                Text("DESEMPEÑO: POR CAPTURAR")
                Spacer(Modifier.height(8.dp))
                Text(
                    "Más información [+]",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        activity.startActivity(
                            Intent(activity, DetalleCuatrimestreActivity::class.java)
                                .putExtra("periodo", "4TO CUATRIMESTRE")
                        )
                    }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(12.dp)) {
                Text("3ER CUATRIMESTRE (MAY-AGO 2025)")
                Text("FINALIZADO")
                Text("CARRERA: TECNOLOGÍAS DE LA INFORM...")
                Text("GRUPO: 3A MATUTINO")
                Text("TUTOR: DRA. GRICELDA RODRIGUEZ R...")
                Text("PROGRESO: 100%")
                Text("DESEMPEÑO: ESTRATÉGICO (E)")
            }
        }
    }
}