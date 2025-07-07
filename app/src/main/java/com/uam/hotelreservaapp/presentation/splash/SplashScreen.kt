package com.uam.hotelreservaapp.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.hotelreservaapp.presentation.navigation.NavRoutes
import com.uam.hotelreservaapp.data.local.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun SplashScreen(
    navController: NavHostController,
    dataStoreManager: DataStoreManager
) {
    val viewModel = SplashViewModel(dataStoreManager)

    viewModel.checkAuth {
        if (it) {
            navController.navigate(NavRoutes.HotelList.route) {
                popUpTo(NavRoutes.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(NavRoutes.Login.route) {
                popUpTo(NavRoutes.Splash.route) { inclusive = true }
            }
        }
    }
}

class SplashViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    fun checkAuth(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val token = dataStoreManager.token.first()
            onResult(token.isNotBlank())
        }
    }
}