package com.uam.hotelreservaapp.presentation.hotels

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uam.hotelreservaapp.data.model.Habitacion
import com.uam.hotelreservaapp.presentation.navigation.NavRoutes
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailScreen(
    hotelId: Int,
    dataStoreManager: com.uam.hotelreservaapp.data.local.DataStoreManager,
    navController: NavHostController
) {
    var habitaciones by remember { mutableStateOf(listOf<Habitacion>()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(hotelId) {
        scope.launch {
            val token = dataStoreManager.token.first()
            val api = createHabitacionApi(token)
            val allHabitaciones = api.getAllHabitaciones()
            habitaciones = allHabitaciones.filter { it.hotelId == hotelId }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Habitaciones disponibles") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            items(habitaciones) { habitacion ->
                HabitacionCard(
                    habitacion = habitacion,
                    onClick = {
                        navController.navigate(NavRoutes.ReservationForm.createRoute(habitacion.id))
                    }
                )
            }
        }
    }
}

@Composable
fun HabitacionCard(habitacion: Habitacion, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Habitación ${habitacion.numero} - ${habitacion.tipo}", style = MaterialTheme.typography.titleMedium)
            Text("Precio: \$${habitacion.precio}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Retrofit personalizado con JWT
fun createHabitacionApi(token: String): HabitacionApi {
    val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(request)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(HabitacionApi::class.java)
}

interface HabitacionApi {
    @GET("/api/habitaciones")
    suspend fun getAllHabitaciones(): List<Habitacion>
    fun getHabitacionesByHotel(i: Int): List<Habitacion>
}