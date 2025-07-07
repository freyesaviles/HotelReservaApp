package com.uam.hotelreservaapp.presentation.reservation

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uam.hotelreservaapp.presentation.navigation.NavRoutes
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationFormScreen(
    habitacionId: Int,
    navController: NavHostController,
    viewModel: ReservationViewModel
) {
    val context = LocalContext.current

    var fechaInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }
    var personas by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    val datePickerInicio = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            calendar.set(year, month, day)
            fechaInicio = dateFormat.format(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val datePickerFin = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            calendar.set(year, month, day)
            fechaFin = dateFormat.format(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Reserva") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { datePickerInicio.show() }) {
                Text(text = if (fechaInicio.isEmpty()) "Seleccionar fecha de entrada" else "Entrada: $fechaInicio")
            }

            Button(onClick = { datePickerFin.show() }) {
                Text(text = if (fechaFin.isEmpty()) "Seleccionar fecha de salida" else "Salida: $fechaFin")
            }

            OutlinedTextField(
                value = personas,
                onValueChange = { personas = it },
                label = { Text("Cantidad de personas") }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Aquí puedes navegar al resumen o guardar los datos temporalmente en un ViewModel
                    navController.navigate(NavRoutes.Summary.route)
                },
                enabled = fechaInicio.isNotEmpty() && fechaFin.isNotEmpty() && personas.isNotEmpty()
            ) {
                Text("Continuar")
            }
        }
    }
}