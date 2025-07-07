package com.uam.hotelreservaapp.presentation.hotels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.hotelreservaapp.data.local.DataStoreManager
import com.uam.hotelreservaapp.data.model.Hotel
import com.uam.hotelreservaapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class HotelsViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    private val _hotels = MutableStateFlow<List<Hotel>>(emptyList())
    val hotels: StateFlow<List<Hotel>> = _hotels

    init {
        fetchHotels()
    }

    private fun fetchHotels() {
        viewModelScope.launch {
            val token = dataStoreManager.token.first()

            val api = createApiWithToken(token)
            val response = api.getHoteles()
            _hotels.value = response
        }
    }

    private fun createApiWithToken(token: String): HotelApi {
        val authInterceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return chain.proceed(request)
            }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(HotelApi::class.java)
    }

    suspend fun logout() {
        dataStoreManager.logout()
    }
}

interface HotelApi {
    @GET("/api/hoteles")
    suspend fun getHoteles(): List<Hotel>
}