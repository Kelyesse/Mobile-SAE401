package com.example.mob_sae401.ui.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImportContacts
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mob_sae401.R

enum class NavBarItems(@StringRes val stringName: Int, val icon: ImageVector, val route: String) {
    HOME(R.string.nav_home, Icons.Default.ImportContacts, Routes.Home.route)
}