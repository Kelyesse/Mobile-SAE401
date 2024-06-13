package com.example.mob_sae401.ui.util

sealed class Routes(val route: String) {
    object Home: Routes("home")
    object Details: Routes("details")
    object Login: Routes("login")
    object Signup: Routes("signup")
    object Reservation: Routes("reservation")
}