package com.uam.hotelreservaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.uam.hotelreservaapp.ui.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LoginScreen { token ->
                    println("JWT: $token") // Luego lo almacenaremos
                    // Aquí puedes navegar a la pantalla principal o mostrar un mensaje
                }
            }
        }
    }
}