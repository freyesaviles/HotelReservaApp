package com.uam.hotelreservaapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uam.hotelreservaapp.data.local.DataStoreManager
import com.uam.hotelreservaapp.presentation.hotels.HotelListScreen
import com.uam.hotelreservaapp.presentation.hotels.HotelsViewModel
import com.uam.hotelreservaapp.presentation.login.LoginScreen
import com.uam.hotelreservaapp.presentation.login.LoginViewModel
import com.uam.hotelreservaapp.presentation.reservation.ConfirmationScreen
import com.uam.hotelreservaapp.presentation.reservation.ReservationFormScreen
import com.uam.hotelreservaapp.presentation.reservation.ReservationViewModel
import com.uam.hotelreservaapp.presentation.reservation.SummaryScreen
import com.uam.hotelreservaapp.presentation.splash.SplashScreen

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(
    navController: NavHostController,
    dataStoreManager: DataStoreManager,
    viewModel: ReservationViewModel
) {
    NavHost(navController = navController, startDestination = NavRoutes.Splash.route) {

        composable(NavRoutes.Login.route) {
            val loginViewModel = LoginViewModel(dataStoreManager)
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = { navController.navigate(NavRoutes.HotelList.route) }
            )
        }

        composable(NavRoutes.HotelList.route) {
            val hotelsViewModel = HotelsViewModel(dataStoreManager)
            HotelListScreen(
                viewModel = hotelsViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.Splash.route) {
            SplashScreen(
                navController = navController,
                dataStoreManager = dataStoreManager
            )
        }

        composable(NavRoutes.ReservationForm.route) { backStackEntry ->
            val habitacionId = backStackEntry.arguments
                ?.getString("habitacionId")
                ?.toIntOrNull()

            habitacionId?.let {
                ReservationFormScreen(
                    habitacionId = it,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }

        composable(NavRoutes.Summary.route) {
            SummaryScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(NavRoutes.Confirmation.route) {
            ConfirmationScreen()
        }
    }
}
