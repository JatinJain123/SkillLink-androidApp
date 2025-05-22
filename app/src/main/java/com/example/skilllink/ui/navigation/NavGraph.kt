package com.example.skilllink.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skilllink.domain.model.local.LocalUiStates
import com.example.skilllink.ui.screens.users.DashBoard
import com.example.skilllink.ui.screens.authScreens.LoginScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val uiStates = LocalUiStates.current
    val isUserSetupComplete = uiStates.isUserSetupComplete

    NavHost(
        navController = navController,
        startDestination = if(isUserSetupComplete) Screens.DashBoard else Screens.LoginScreen
    ) {
        composable<Screens.LoginScreen> {
            LoginScreen(
                navigate = { navController.navigate(Screens.DashBoard) }
            )
        }

        composable<Screens.DashBoard> {
            DashBoard()
        }
    }
}