package com.example.appsigo.ui.login

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appsigo.SigoLoginApplication
import com.example.appsigo.di.LoginViewModelFactory
import com.example.appsigo.ui.main.MainActivity

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
    val context = LocalApp.current // ✅ obtener contexto aquí

    LaunchedEffect(state.loginSuccess) {
        if (state.loginSuccess) {
            val intent = Intent(context, MainActivity::class.java)
            onNavigateMain(intent)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo / título
        Text("SIGO - UTM", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("BIENVENIDO DE REGRESO", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(24.dp))

        // Campo usuario
        OutlinedTextField(
            value = state.username,
            onValueChange = vm::onUsernameChange,
            label = { Text("USUARIO") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // Campo contraseña
        OutlinedTextField(
            value = state.password,
            onValueChange = vm::onPasswordChange,
            label = { Text("CONTRASEÑA") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        // Botón login
        Button(
            onClick = { vm.login() },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.isLoading) "Iniciando..." else "INICIAR SESIÓN")
        }

        Spacer(Modifier.height(12.dp))

        // Link estático
        Text(
            "¿OLVIDASTE TU CONTRASEÑA?",
            style = MaterialTheme.typography.bodyMedium,
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