package com.uam.hotelreservaapp.data.model

data class Habitacion(
    val id: Int,
    val numero: String,
    val tipo: String,
    val precio: Double,
    val hotelId: Int
)