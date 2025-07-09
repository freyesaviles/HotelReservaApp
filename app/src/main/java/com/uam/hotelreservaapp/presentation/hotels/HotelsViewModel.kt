package com.uam.hotelreservaapp.presentation.hotels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.hotelreservaapp.data.local.DataStoreManager
import com.uam.hotelreservaapp.data.model.Hotel
import com.uam.hotelreservaapp.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HotelsViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    private val _hotels = MutableStateFlow<List<Hotel>>(emptyList())
    val hotels: StateFlow<List<Hotel>> = _hotels

    init {
        fetchHotels()
    }

    private fun fetchHotels() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getHoteles()
                _hotels.value = response
            } catch (e: Exception) {
                _hotels.value = emptyList() // o maneja el error como prefieras
            }
        }
    }

    suspend fun logout() {
        dataStoreManager.logout()
    }
}
