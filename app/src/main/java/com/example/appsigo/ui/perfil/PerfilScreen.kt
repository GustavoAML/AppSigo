package com.example.appsigo.ui.perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PerfilScreen() {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text("PERFIL", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text("ID: UTM214041BT1")
        Spacer(Modifier.height(8.dp))
        Text("NOMBRE: Juan")
        Text("PRIMER APELLIDO: Pérez")
        Text("SEGUNDO APELLIDO: López")
        Text("ESTADO DE NACIMIENTO: Michoacán")
        Text("SEXO: HOMBRE")
        Text("CURP: XXXXXXZZZZYYYY")
        Text("NÚMERO DE SEGURO SOCIAL: 123-45-6789")
    }
}