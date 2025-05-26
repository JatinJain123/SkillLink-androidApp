package com.example.skilllink.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skilllink.domain.model.local.LocalUiStates
import com.example.skilllink.ui.screens.userScreens.DashBoard
import com.example.skilllink.ui.screens.authScreens.LoginScreen1
import com.example.skilllink.ui.screens.authScreens.LoginScreen2
import com.example.skilllink.ui.screens.authScreens.LoginScreen3
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
            isUserSetupComplete -> Screens.DashBoard
            isLogged && hasUsername -> Screens.LoginScreen3
            isLogged -> Screens.LoginScreen2
            else -> Screens.LoginScreen1
        }
    ) {
        // Login screen step 1
        composable<Screens.LoginScreen1>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(AppConstants.SCREEN_TRANSITION_TIME),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(AppConstants.SCREEN_TRANSITION_TIME)
                )
            }
        ) {
            LoginScreen1(navController = navController)
        }

        // Login screen step 2
        composable<Screens.LoginScreen2>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(AppConstants.SCREEN_TRANSITION_TIME),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(AppConstants.SCREEN_TRANSITION_TIME)
                )
            }
        ) {
            LoginScreen2(navController = navController)
        }


        // Login Screen step 3
        composable<Screens.LoginScreen3>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(AppConstants.SCREEN_TRANSITION_TIME),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(AppConstants.SCREEN_TRANSITION_TIME)
                )
            }
        ) {
            LoginScreen3(navController = navController)
        }

        // Dash Board
        composable<Screens.DashBoard> {
            DashBoard()
        }
    }
}