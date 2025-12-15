package com.example.appsigo.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import com.example.appsigo.SigoLoginApplication
import com.example.appsigo.ui.main.MainActivity

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Inyectamos la aplicación en el CompositionLocal
                CompositionLocalProvider(LocalApp provides application as SigoLoginApplication) {
                    // Pantalla de bienvenida (login)
                    WelcomeScreen(
                        onNavigateMain = { userResponse ->
                            // Guardar el usuario en AppContainer
                            (application as SigoLoginApplication).appContainer.usuarioActual = userResponse

                            // Crear el intent para abrir MainActivity
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}
