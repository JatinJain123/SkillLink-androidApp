package com.example.skilllink.ui.screens.learnerScreens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun NotificationScreen(
    navController: NavController
) {
    Text(
        text = "this is learnerScreens / home / NotificationScreen",
        textAlign = TextAlign.Center
    )
}