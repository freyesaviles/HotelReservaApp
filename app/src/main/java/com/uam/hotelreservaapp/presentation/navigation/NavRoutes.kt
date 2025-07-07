package com.uam.hotelreservaapp.presentation.navigation

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object HotelList : NavRoutes("hotel_list")
    object Splash : NavRoutes("splash")
    object HotelDetail : NavRoutes("hotel_detail/{hotelId}") {
        fun createRoute(hotelId: Int) = "hotel_detail/$hotelId"
    }
    object ReservationForm : NavRoutes("reservation_form/{habitacionId}") {
        fun createRoute(habitacionId: Int) = "reservation_form/$habitacionId"
    }
    object Summary : NavRoutes("summary")
    object Confirmation : NavRoutes("confirmation")
}