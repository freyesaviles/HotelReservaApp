package com.uam.hotelreservaapp.data.remote

import com.uam.hotelreservaapp.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    @GET("api/hoteles")
    suspend fun getHoteles(): List<Hotel>

    @GET("api/habitaciones")
    suspend fun getAllHabitaciones(): List<Habitacion>

    @POST("api/reservas")
    suspend fun crearReserva(@Body reserva: Reserva)
}
