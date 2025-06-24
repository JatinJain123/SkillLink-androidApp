package com.example.skilllink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skilllink.data.local.providers.UserPrefsStoreManagerProvider
import com.example.skilllink.domain.model.local.AppDependencies
import com.example.skilllink.domain.model.local.LocalAppDependencies
import com.example.skilllink.domain.model.local.LocalUiStates
import com.example.skilllink.domain.model.local.UiStates
import com.example.skilllink.ui.navigation.NavGraph
import com.example.skilllink.ui.reusableComponents.SplashScreen
import com.example.skilllink.ui.theme.SkillLinkTheme
import com.example.skilllink.ui.viewModels.AppStoreViewModel
import com.example.skilllink.ui.viewModels.UserPrefsStoreViewModel
import com.example.skilllink.ui.viewModels.UserPrefsStoreViewModelFactory
import com.example.skilllink.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            val applicationContext = LocalContext.current.applicationContext
            val appStoreViewModel = hiltViewModel<AppStoreViewModel>()
            val userId by appStoreViewModel.currentUser.collectAsState()
            val email by appStoreViewModel.currentEmail.collectAsState()

            var userPrefsStoreViewModel: UserPrefsStoreViewModel? by remember { mutableStateOf(null) }
            var isDarkTheme by remember { mutableStateOf(true) }
            var isLogged by remember { mutableStateOf(false) }
            var hasUsername by remember { mutableStateOf(false) }
            var hasSecretPin by remember { mutableStateOf(false) }
            var isUserSetupComplete by remember { mutableStateOf(false) }

            var minDelay by remember { mutableStateOf(false) }
            var isInitialSetupComplete by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                delay(AppConstants.MIN_SPLASH_SCREEN_TIME)
                minDelay = true
            }

            LaunchedEffect(userId, email) {
                if (userId.isNotBlank()) {
                    try {
                        val userPrefsStoreManager = UserPrefsStoreManagerProvider
                            .provideUserPrefsStoreManager(context = applicationContext, userId = userId)

                        val factory = UserPrefsStoreViewModelFactory(userPrefsStoreManager)

                        userPrefsStoreViewModel = ViewModelProvider(
                            this@MainActivity, factory
                        )[UserPrefsStoreViewModel::class.java]

                        userPrefsStoreViewModel?.apply {
                            setId(userId)
                            setEmail(email)
                        }
                        isLogged = true
                    } catch (e: Exception) {
                        e.stackTrace
                    }
                } else {
                    isInitialSetupComplete = true
                }
            }

            userPrefsStoreViewModel?.let { model ->
                val username by model.userName.collectAsState()
                val hasSpin by model.hasSpin.collectAsState()
                val lightMode by model.lightMode.collectAsState()

                LaunchedEffect(username, hasSpin, lightMode) {
                    isDarkTheme = !lightMode
                    hasUsername = username.isNotEmpty()
                    hasSecretPin = hasSpin
                    isUserSetupComplete = hasUsername && hasSecretPin
                    isInitialSetupComplete = true
                }
            }

            if(minDelay && isInitialSetupComplete) {
                val appDependencies = AppDependencies(
                    appStoreViewModel = appStoreViewModel,
                    userPrefsStoreViewModel = userPrefsStoreViewModel
                )
                val uiStates = UiStates(
                    isLogged = isLogged,
                    hasUsername = hasUsername,
                    hasSecretPin = hasSecretPin,
                    isUserSetupComplete = isUserSetupComplete,
                    isDarkTheme = isDarkTheme
                )

                CompositionLocalProvider(
                    LocalAppDependencies provides appDependencies,
                    LocalUiStates provides uiStates
                ) {
                    SkillLinkTheme(darkTheme = isDarkTheme) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                        ){
                            NavGraph()
                        }
                    }
                }
            } else {
                SplashScreen()
            }
        }
    }
}