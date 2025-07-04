package com.uam.hotelreservaapp.data.remote

import com.uam.hotelreservaapp.data.model.LoginRequest
import com.uam.hotelreservaapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}