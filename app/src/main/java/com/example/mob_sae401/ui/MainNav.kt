package com.example.mob_sae401.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mob_sae401.PreferencesManager
import com.example.mob_sae401.data.implementation.WorkList
import com.example.mob_sae401.ui.detail.DetailPage
import com.example.mob_sae401.ui.homepage.HomePage
import com.example.mob_sae401.ui.login.LoginPage
import com.example.mob_sae401.ui.reservation.ReservationPage
import com.example.mob_sae401.ui.util.Routes

@Composable
fun MainNav(navController: NavHostController, scaffoldPadding: PaddingValues) {

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val data = remember {
        mutableStateOf(preferencesManager.getData("connected", "blank"))
    }

    NavHost(
        navController = navController,
        startDestination = if (data.value != "blank") Routes.Home.route else Routes.Login.route,
        modifier = Modifier.padding(scaffoldPadding)
    ) {
        composable(Routes.Home.route) {
            HomePage(navController)
        }
        composable(
            "${Routes.Details.route}?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val work = WorkList.list.find { it.id == backStackEntry.arguments?.getInt("id") }

            DetailPage(work)
        }
        composable(Routes.Login.route) {
            LoginPage(navController)
        }
        composable(Routes.Reservation.route) {
            ReservationPage()
        }
    }
}