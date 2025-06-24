package com.example.skilllink.ui.screens.commonComponents.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.skilllink.R
import com.example.skilllink.ui.navigation.Screens

data class BottomNavItem<T: Any>(
    val title: String,
    val route: T,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    val selectedIconDrawable: Int? = null,
    val unselectedIconDrawable: Int? = null,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val bottomNavItemList = listOf(
    BottomNavItem(
        title = "Home",
        route = Screens.Home.HomeScreen,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false
    ),
    BottomNavItem(
        title = "Search",
        route = Screens.Search.SearchScreen,
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        hasNews = false
    ),
    BottomNavItem(
        title = "My Learning",
        route = Screens.MyLearning.MyLearningScreen,
        selectedIconDrawable = R.drawable.lightbulb_filled_icon,
        unselectedIconDrawable = R.drawable.outline_lightbulb_icon,
        hasNews = false
    ),
    BottomNavItem(
        title = "Create",
        route = Screens.Creator.CreatorScreen,
        selectedIconDrawable = R.drawable.filled_ondemand_video_icon,
        unselectedIconDrawable = R.drawable.filled_ondemand_video_icon,
        hasNews = false
    ),
    BottomNavItem(
        title = "Profile",
        route = Screens.Profile.ProfileScreen,
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false
    )
)
