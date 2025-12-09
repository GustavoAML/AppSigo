package com.example.appsigo.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.appsigo.R

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            HeaderLogos()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "BIENVENIDO DE REGRESO",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
            UsernameField()
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField()
            Spacer(modifier = Modifier.height(24.dp))
            LoginButton()
            Spacer(modifier = Modifier.height(16.dp))
            ForgotPasswordLink()
        }
    }
}

@Composable
fun HeaderLogos() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sigo),
            contentDescription = "Logo SIGO",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = R.drawable.utm),
            contentDescription = "Logo UTM",
            modifier = Modifier.size(150.dp)
        )
    }
}

@Composable
fun UsernameField() {
    var username by remember { mutableStateOf("") }
    OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        label = { Text("USUARIO") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Icono usuario"
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordField() {
    var password by remember { mutableStateOf("") }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("CONTRASEÑA") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Icono contraseña"
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LoginButton() {
    Button(
        onClick = { /* lógica de login */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text("INICIAR SESIÓN")
    }
}

@Composable
fun ForgotPasswordLink() {
    Text(
        text = "¿OLVIDASTE TU CONTRASEÑA?",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.clickable { /* lógica de recuperación */ }
    )
}