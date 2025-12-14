package com.example.appsigo.ui.login

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appsigo.R
import com.example.appsigo.SigoLoginApplication
import com.example.appsigo.di.LoginViewModelFactory
import com.example.appsigo.ui.main.MainActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun WelcomeScreen(
    vm: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(
            (LocalApp.current as SigoLoginApplication).appContainer.authRepository
        )
    ),
    onNavigateMain: (Intent) -> Unit
) {
    val state by vm.uiState.collectAsState()
    val context = LocalApp.current

    LaunchedEffect(state.loginSuccess) {
        if (state.loginSuccess) {
            val intent = Intent(context, MainActivity::class.java)
            onNavigateMain(intent)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(48.dp))

        // Logos SIGO y UTM
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Image(
                painter = painterResource(id = R.drawable.sigo),
                contentDescription = "Logo SIGO",
                modifier = Modifier.size(148.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.utm),
                contentDescription = "Logo UTM",
                modifier = Modifier.size(148.dp)
            )
        }

        Spacer(Modifier.height(36.dp))
        Text("BIENVENIDO DE REGRESO", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(24.dp))

        // Campo USUARIO con ícono (siempre en mayúsculas)
        OutlinedTextField(
            value = state.username,
            onValueChange = { vm.onUsernameChange(it.uppercase()) }, // 👈 convierte a mayúsculas
            label = { Text("USUARIO") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Ícono de usuario"
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // Campo CONTRASEÑA con ícono y botón de ojo
        var passwordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = state.password,
            onValueChange = vm::onPasswordChange,
            label = { Text("CONTRASEÑA") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Ícono de contraseña"
                )
            },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        // Botón INICIAR SESIÓN
        Button(
            onClick = { vm.login() },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.isLoading) "Iniciando..." else "INICIAR SESIÓN")
        }

        Spacer(Modifier.height(16.dp))

        // Enlace ¿OLVIDASTE TU CONTRASEÑA?
        Text(
            "¿OLVIDASTE TU CONTRASEÑA?",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { /* acción estática */ }
        )

        // Mostrar error si existe
        state.errorMessage?.let { errorMsg ->
            Spacer(Modifier.height(12.dp))
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}