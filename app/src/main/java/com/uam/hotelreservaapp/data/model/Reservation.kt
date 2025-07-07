package com.uam.hotelreservaapp.data.model

data class Reserva(
    val id: Int? = null,
    val fechaInicio: String,
    val fechaFin: String,
    val personas: Int,
    val habitacionId: Int,
    val usuarioId: Int // Puedes obtenerlo desde el token si lo necesitas
)