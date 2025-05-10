package com.example.skilllink.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skilllink.ui.screens.users.DashBoard
import com.example.skilllink.ui.screens.animatedComponents.SplashScreen
import com.example.skilllink.ui.screens.authScreens.LoginScreen
import com.example.skilllink.ui.viewModels.AppStoreViewModel
import com.example.skilllink.ui.theme.SkillLinkTheme

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val appStoreModel = hiltViewModel<AppStoreViewModel>()
    val currentUser by appStoreModel.currentUser.collectAsState()

    var navigate: () -> Unit = { navController.navigate(Screens.LoginScreen) }
    var userIsPresent = false
    if(currentUser.isNotBlank()) {
        userIsPresent = true
        navigate = { navController.navigate(Screens.DashBoard) }
    }

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen
    ) {
        composable<Screens.SplashScreen> {
            SplashScreen(
                userIsPresent = userIsPresent,
                navigation = navigate
            )
        }

        composable<Screens.LoginScreen> {
            SkillLinkTheme(darkTheme = true) {
                LoginScreen(
                    navigate = {navController.navigate(Screens.DashBoard)}
                )
            }
        }

        composable<Screens.DashBoard> {
            SkillLinkTheme(darkTheme = false) {
                DashBoard()
            }
        }
    }
}