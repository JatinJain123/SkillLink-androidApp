package com.example.skilllink.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skilllink.domain.model.local.LocalUiStates
import com.example.skilllink.ui.screens.users.DashBoard
import com.example.skilllink.ui.screens.authScreens.LoginScreen
import com.example.skilllink.utils.AppConstants

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val uiStates = LocalUiStates.current
    val isUserSetupComplete = uiStates.isUserSetupComplete

    NavHost(
        navController = navController,
        startDestination = if(isUserSetupComplete) Screens.DashBoard else Screens.LoginScreen
    ) {
        composable<Screens.LoginScreen>(
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
            LoginScreen(
                navigate = {
                    navController.navigate(Screens.DashBoard) {
                        popUpTo<Screens.LoginScreen> {
                            inclusive = true
                            saveState = true
                        }
                    }
                }
            )
        }

        composable<Screens.DashBoard> {
            DashBoard()
        }
    }
}