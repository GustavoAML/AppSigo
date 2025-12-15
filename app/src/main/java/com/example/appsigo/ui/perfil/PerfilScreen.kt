package com.example.appsigo.ui.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appsigo.data.model.UserResponse
import com.example.appsigo.theme.primaryLight

@Composable
fun PerfilScreen(user: UserResponse) {
    Column(Modifier.fillMaxWidth()
        .padding(12.dp)
        .background(MaterialTheme.colorScheme.background)){
        Text("PERFIL DEL ESTUDIANTE", style = MaterialTheme.typography.titleLarge,
            color = primaryLight
        )
        Spacer(Modifier.height(8.dp))
        Card (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp)){
            Column(Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Nombre Completo: ${user.personFullName}")
                Text("Usuario: ${user.username}")
                Text("Email: ${user.email}")

                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text("Perfil: ${user.profileName}")
                Text("Módulo de Acceso: ${user.accessModule}")
                Text("Roles: ${user.roles.joinToString()}")

                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text("ID de Persona: ${user.personId}")
                Text("ID de Registro: ${user.id}")
                Text("Usuario Activo: ${if (user.active) "Sí" else "No"}")
                Text("Registro: ${user.register}")
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text("Términos y Condiciones: ${if (user.termsConditions) "Aceptados" else "No aceptados"}")
            }
        }
    }
    }
