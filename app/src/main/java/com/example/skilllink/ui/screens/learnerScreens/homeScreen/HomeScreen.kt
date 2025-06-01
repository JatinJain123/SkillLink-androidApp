package com.example.skilllink.ui.screens.learnerScreens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skilllink.domain.model.local.LocalAppDependencies
import com.example.skilllink.ui.reusableComponents.cards.CircularReelsCard
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.ui.theme.LocalCustomColors
import com.example.skilllink.ui.theme.SkillLinkTheme
import com.example.skilllink.ui.theme.VeryLightGray
import com.example.skilllink.utils.testReelData


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
        bottomBar = {
            BottomNavigationBar(
                customFields = customFields,
                navController = navController,
                selectedItemIndex = selectedItemIndex,
                onClick = { index -> selectedItemIndex = index }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                userName = userName,
                profilePic = profilePic,
                customFields = customFields,
                navController = navController
            )

            FollowersScroll(
                customFields = customFields,
                navController = navController
            )

//            TrendingScroll(
//                customFields = customFields,
//                navController = navController
//            )
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