package com.example.skilllink.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skilllink.domain.model.local.LocalUiStates
import com.example.skilllink.ui.screens.authScreens.LoginScreen
import com.example.skilllink.ui.screens.authScreens.SecretPinScreen
import com.example.skilllink.ui.screens.authScreens.UserNameScreen
import com.example.skilllink.ui.screens.learnerScreens.homeScreen.HomeScreen
import com.example.skilllink.utils.AppConstants

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val uiStates = LocalUiStates.current
    val isLogged = uiStates.isLogged
    val hasUsername = uiStates.hasUsername
    val isUserSetupComplete = uiStates.isUserSetupComplete

    NavHost(
        navController = navController,
        startDestination = when {
            isUserSetupComplete -> Screens.HomeScreens.HomeScreen
            isLogged && hasUsername -> Screens.AuthScreens.SecretPinScreen
            isLogged -> Screens.AuthScreens.UserNameScreen
            else -> Screens.AuthScreens.LoginScreen
        }
    ) {
        // Login screen step 1
        composable<Screens.AuthScreens.LoginScreen> {
            LoginScreen(navController = navController)
        }

        // Login screen step 2
        composable<Screens.AuthScreens.UserNameScreen> {
            UserNameScreen(navController = navController)
        }

        // Login Screen step 3
        composable<Screens.AuthScreens.SecretPinScreen>(
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(AppConstants.SCREEN_TRANSITION_TIME)
                )
            },
            popExitTransition = { null }
        ) {
            SecretPinScreen(navController = navController)
        }

        // Home Screen
        composable<Screens.HomeScreens.HomeScreen> {
            HomeScreen(navController = navController)
        }
    }
}