package com.uam.hotelreservaapp.presentation.hotels

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uam.hotelreservaapp.data.model.Hotel
import com.uam.hotelreservaapp.presentation.navigation.NavRoutes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelListScreen(viewModel: HotelsViewModel, navController: NavHostController) {
    val hotels by viewModel.hotels.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hoteles Disponibles") },
                actions = {
                    TextButton(onClick = {
                        scope.launch {
                            viewModel.logout()
                            navController.navigate(NavRoutes.Login.route) {
                                popUpTo(0) { inclusive = true } // limpia el backstack
                            }
                        }
                    }) {
                        Text("Cerrar sesión", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(hotels) { hotel ->
                HotelCard(
                    hotel = hotel,
                    onClick = {
                        navController.navigate(NavRoutes.HotelDetail.createRoute(hotel.id))
                    }
                )
            }
        }
    }
}
