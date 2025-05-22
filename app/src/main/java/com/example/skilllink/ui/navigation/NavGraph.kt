package com.example.skilllink.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skilllink.ui.screens.users.DashBoard
import com.example.skilllink.ui.screens.authScreens.LoginScreen
import com.example.skilllink.ui.viewModels.AppStoreViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen
    ) {
        composable<Screens.LoginScreen> {
            LoginScreen(
                navigate = {navController.navigate(Screens.DashBoard)}
            )
        }

        composable<Screens.DashBoard> {
            DashBoard()
        }
    }
}