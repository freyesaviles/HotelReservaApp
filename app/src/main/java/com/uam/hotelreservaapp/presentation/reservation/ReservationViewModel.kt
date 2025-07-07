package com.uam.hotelreservaapp.presentation.reservation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.hotelreservaapp.data.local.DataStoreManager
import com.uam.hotelreservaapp.data.model.Reserva
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class ReservationViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    private val _reserva = MutableStateFlow<Reserva?>(null)
    val reserva: StateFlow<Reserva?> = _reserva

    fun guardarReservaTemporal(fechaInicio: String, fechaFin: String, personas: Int, habitacionId: Int) {
        _reserva.value = Reserva(
            fechaInicio = fechaInicio,
            fechaFin = fechaFin,
            personas = personas,
            habitacionId = habitacionId,
            usuarioId = 1 // ← temporal si no obtienes del token
        )
    }

    fun confirmarReserva(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val token = dataStoreManager.token.first()
            val reservaActual = _reserva.value ?: return@launch

            try {
                val api = createReservaApi(token)
                api.crearReserva(reservaActual)
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Error al guardar reserva")
            }
        }
    }

    private fun createReservaApi(token: String): ReservaApi {
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

        return retrofit.create(ReservaApi::class.java)
    }
}

interface ReservaApi {
    @POST("/api/reservas")
    suspend fun crearReserva(@Body reserva: Reserva)
}