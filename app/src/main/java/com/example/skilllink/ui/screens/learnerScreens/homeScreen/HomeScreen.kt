package com.example.skilllink.ui.screens.learnerScreens.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skilllink.domain.model.local.LocalAppDependencies
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.ui.theme.LocalCustomColors
import com.example.skilllink.ui.theme.SkillLinkTheme
import com.example.skilllink.ui.theme.VeryLightGray


@Composable
fun HomeScreen(
    navController: NavController
) {
    val appDependencies = LocalAppDependencies.current
    val userPrefsStoreViewModel = appDependencies.userPrefsStoreViewModel
    var userName by remember { mutableStateOf("User") }
    var profilePic by remember { mutableStateOf("") }

    userPrefsStoreViewModel?.let {
        val name by it.userName.collectAsState()
        val picUri by it.profilePic.collectAsState()

        LaunchedEffect(name, picUri) {
            userName = name
            profilePic = picUri
        }
    }

    ScreenView(
        userName = userName,
        profilePic = profilePic,
        navController = navController
    )
}

@Composable
fun ScreenView(
    userName: String,
    profilePic: String,
    navController: NavController
) {
    val customFields = LocalCustomColors.current
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Header(
                userName = userName,
                profilePic = profilePic,
                customFields = customFields,
                navController = navController
            )
        },
        bottomBar = {
            BottomNavigationBar(
                customFields = customFields,
                navController = navController,
                selectedItemIndex = selectedItemIndex,
                onClick = { index -> selectedItemIndex = index }
            )
        }
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                FollowersScroll(
                    customFields = customFields,
                    navController = navController
                )
            }

            item {
                TrendingScroll(
                    customFields = customFields,
                    navController = navController
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenViewPreview() {
    SkillLinkTheme(darkTheme = true) {
        CompositionLocalProvider(LocalCustomColors provides darkCustomFields) {
            ScreenView(
                userName = "jatin_jain",
                profilePic = "",
                navController = rememberNavController()
            )
        }
    }
}

private val darkCustomFields = CustomFields(
    primaryTextColor = VeryLightGray,
    secondaryTextColor = Color.LightGray,
    inverseBg = Color.White,
    iconTint = Color.LightGray,
    primaryFocusedColor = Color.White,
    primaryUnfocusedColor = Color.LightGray
)