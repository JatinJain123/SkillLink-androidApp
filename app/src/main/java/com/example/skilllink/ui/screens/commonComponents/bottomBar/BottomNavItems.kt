package com.example.skilllink.ui.screens.commonComponents.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.skilllink.R
import com.example.skilllink.ui.navigation.Screens

data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    val selectedIconDrawable: Int? = null,
    val unselectedIconDrawable: Int? = null,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val navigation: (NavController) -> Unit
)

val bottomNavItemList = listOf(
    BottomNavItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        navigation = { navController ->  navController.navigate(Screens.Home.HomeScreen)}
    ),
    BottomNavItem(
        title = "Search",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        hasNews = false,
        navigation = { navController -> navController.navigate(Screens.Search.SearchScreen) }
    ),
    BottomNavItem(
        title = "My Learning",
        selectedIconDrawable = R.drawable.lightbulb_filled_icon,
        unselectedIconDrawable = R.drawable.outline_lightbulb_icon,
        hasNews = false,
        navigation = {}
    ),
    BottomNavItem(
        title = "Create",
        selectedIconDrawable = R.drawable.filled_ondemand_video_icon,
        unselectedIconDrawable = R.drawable.filled_ondemand_video_icon,
        hasNews = false,
        navigation = {}
    ),
    BottomNavItem(
        title = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false,
        navigation = { navController -> navController.navigate(Screens.Profile.ProfileScreen) }
    )
)
