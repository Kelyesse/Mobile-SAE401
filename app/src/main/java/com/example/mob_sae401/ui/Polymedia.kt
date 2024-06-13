package com.example.mob_sae401.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mob_sae401.ui.theme.Red
import com.example.mob_sae401.ui.util.NavBarItems
import com.example.mob_sae401.ui.util.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Polymedia() {

    val navController = rememberNavController()

    val navBarStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBarStackEntry?.destination

    Scaffold(topBar = {
        if (currentDestination?.route != "login")
            TopAppBar(
                title = { },
                navigationIcon = {
                    Text(
                        text = "P*",
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        modifier = Modifier.padding(start = 18.dp)
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Routes.Reservation.route) {
                                launchSingleTop = true
                            }
                        },
                        modifier = Modifier.padding(end = 18.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Red,
                    navigationIconContentColor = Color(0XFFEEEEEE),
                    actionIconContentColor = Color(0XFFEEEEEE)
                )
            )
    }, bottomBar = {
        BottomBar(navController = navController, currentDestination)
    }) { paddingValues ->
        MainNav(navController = navController, scaffoldPadding = paddingValues)
    }
}

@Composable
fun BottomBar(navController: NavController, currentDestination: NavDestination?) {
    val items = NavBarItems.values()

    if (currentDestination?.route != "login")
        NavigationBar(containerColor = Red) {
            items.forEach { item ->
                val selected = currentDestination?.hierarchy?.any {
                    if (it.route != null) it.route!!.startsWith(item.route) else it.route == item.route
                } == true

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.White,
                        indicatorColor = Red
                    )
                )
            }
        }

}