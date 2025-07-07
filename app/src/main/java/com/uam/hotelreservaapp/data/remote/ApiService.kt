package com.uam.hotelreservaapp.data.remote

import com.uam.hotelreservaapp.data.model.AuthRequest
import com.uam.hotelreservaapp.data.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/auth/login")
    suspend fun login(@Body request: AuthRequest): AuthResponse
}