package com.example.appsigo.ui.historial

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appsigo.theme.primaryLight

@Composable
fun HistorialScreen(activity: ComponentActivity) {
    Column(Modifier.fillMaxWidth()
        .padding(12.dp)
        .background(MaterialTheme.colorScheme.background)) {
        Text("HISTORIAL ACADÉMICO", style = MaterialTheme.typography.titleLarge,
            color = primaryLight
        )
        Spacer(Modifier.height(8.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp)) {
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
                    color = primaryLight,
                    modifier = Modifier.clickable {
                        activity.startActivity(
                            Intent(activity, DetalleCuatrimestreActivity::class.java)
                                .putExtra("periodo", "4TO CUATRIMESTRE")
                        )
                    }
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp)) {
            Column(Modifier.padding(12.dp)) {
                Text("3ER CUATRIMESTRE (MAY-AGO 2025)")
                Text("FINALIZADO")
                Text("CARRERA: TECNOLOGÍAS DE LA INFORM...")
                Text("GRUPO: 3A MATUTINO")
                Text("TUTOR: DRA. GRICELDA RODRIGUEZ R...")
                Text("PROGRESO: 100%")
                Text("DESEMPEÑO: ESTRATÉGICO (E)")
                Spacer(Modifier.height(8.dp))
                Text(
                    "Más información [+]",
                    color = primaryLight,
                    modifier = Modifier.clickable {
                        activity.startActivity(
                            Intent(activity, DetalleCuatrimestreActivity::class.java)
                                .putExtra("periodo", "4TO CUATRIMESTRE")
                        )
                    }
                )
            }
        }
    }
}