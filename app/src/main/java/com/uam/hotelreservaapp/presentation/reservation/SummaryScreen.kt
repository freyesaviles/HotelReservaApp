package com.uam.hotelreservaapp.presentation.reservation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uam.hotelreservaapp.presentation.navigation.NavRoutes

@Composable
fun SummaryScreen(viewModel: ReservationViewModel, navController: NavHostController) {
    val reserva by viewModel.reserva.collectAsState()

    reserva?.let {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Resumen de Reserva", style = MaterialTheme.typography.headlineSmall)

            Text("Fecha Inicio: ${it.fechaInicio}")
            Text("Fecha Fin: ${it.fechaFin}")
            Text("Personas: ${it.personas}")
            Text("Habitación ID: ${it.habitacionId}")

            Button(onClick = {
                viewModel.confirmarReserva(
                    onSuccess = {
                        navController.navigate(NavRoutes.Confirmation.route) {
                            popUpTo(NavRoutes.Summary.route) { inclusive = true }
                        }
                    },
                    onError = { msg ->
                        // Mostrar snackbar, log, etc.
                    }
                )
            }) {
                Text("Confirmar Reserva")
            }
        }
    } ?: Text("No hay datos de reserva")
}