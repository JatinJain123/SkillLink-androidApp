package com.example.skilllink.ui.screens.learnerScreens.creator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.skilllink.ui.screens.commonComponents.bottomBar.BottomNavigationBar
import com.example.skilllink.ui.theme.LocalCustomColors

@Composable
fun CreatorScreen(
    navController: NavController
) {
    val customFields = LocalCustomColors.current

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                customFields = customFields,
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "this is learnerScreens / creator / CreatorScreen")
        }
    }
}