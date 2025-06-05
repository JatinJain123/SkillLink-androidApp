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
import com.example.skilllink.ui.screens.learnerScreens.chatRoom.ChatRoomScreen
import com.example.skilllink.ui.screens.learnerScreens.creator.CreatorScreen
import com.example.skilllink.ui.screens.learnerScreens.home.HomeScreen
import com.example.skilllink.ui.screens.learnerScreens.home.NotificationScreen
import com.example.skilllink.ui.screens.learnerScreens.myLearning.MyLearningScreen
import com.example.skilllink.ui.screens.learnerScreens.profile.ProfileScreen
import com.example.skilllink.ui.screens.learnerScreens.search.SearchScreen
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
            isUserSetupComplete -> Screens.Home.HomeScreen
            isLogged && hasUsername -> Screens.Auth.SecretPinScreen
            isLogged -> Screens.Auth.UserNameScreen
            else -> Screens.Auth.LoginScreen
        }
    ) {
        // Login screen step 1
        composable<Screens.Auth.LoginScreen> {
            LoginScreen(navController = navController)
        }

        // Login screen step 2
        composable<Screens.Auth.UserNameScreen> {
            UserNameScreen(navController = navController)
        }

        // Login Screen step 3
        composable<Screens.Auth.SecretPinScreen>(
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
        composable<Screens.Home.HomeScreen> {
            HomeScreen(navController = navController)
        }

        // Notification Screen
        composable<Screens.Home.NotificationScreen> {
            NotificationScreen(navController = navController)
        }

        // Chat Room Screen
        composable<Screens.ChatRoom.ChatRoomScreen> {
            ChatRoomScreen(navController = navController)
        }

        // Search Courses Screen
        composable<Screens.Search.SearchScreen> {
            SearchScreen(navController = navController)
        }

        // Search Courses Screen
        composable<Screens.MyLearning.MyLearningScreen> {
            MyLearningScreen(navController = navController)
        }

        // Search Courses Screen
        composable<Screens.Creator.CreatorScreen> {
            CreatorScreen(navController = navController)
        }

        // Profile Screen
        composable<Screens.Profile.ProfileScreen> {
            ProfileScreen(navController = navController)
        }
    }
}