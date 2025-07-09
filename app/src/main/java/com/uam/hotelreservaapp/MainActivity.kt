package com.uam.hotelreservaapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.uam.hotelreservaapp.data.local.DataStoreManager
import com.uam.hotelreservaapp.presentation.navigation.AppNavHost
import com.uam.hotelreservaapp.presentation.reservation.ReservationViewModel
import com.uam.hotelreservaapp.ui.theme.HotelReservaAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStoreManager = DataStoreManager(applicationContext)

        // Aquí inicializas RetrofitInstance con el dataStoreManager
        com.uam.hotelreservaapp.data.remote.RetrofitInstance.dataStoreManager = dataStoreManager

        setContent {
            HotelReservaAppTheme {
                val navController = rememberNavController()

                val reservationViewModel = ReservationViewModel(dataStoreManager)

                AppNavHost(
                    navController = navController,
                    dataStoreManager = dataStoreManager,
                    viewModel = reservationViewModel
                )
            }
        }
    }
}
