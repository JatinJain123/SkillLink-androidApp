package com.example.skilllink.ui.screens.authScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllink.R
import com.example.skilllink.domain.model.local.UiEvent
import com.example.skilllink.ui.navigation.Screens
import com.example.skilllink.ui.reusableComponents.Dots
import com.example.skilllink.ui.reusableComponents.buttons.GradientButton
import com.example.skilllink.ui.reusableComponents.inputFields.StyledInputField
import com.example.skilllink.ui.theme.LocalCustomColors
import com.example.skilllink.ui.viewModels.AppStoreViewModel
import com.example.skilllink.ui.viewModels.AuthViewModel
import com.example.skilllink.ui.viewModels.UserPrefsStoreViewModel
import com.example.skilllink.utils.AppConstants

@Composable
fun UsernameScreen(
    authViewModel: AuthViewModel,
    appStoreViewModel: AppStoreViewModel,
    userPrefsStoreViewModel: UserPrefsStoreViewModel?,
    navController: NavController
) {
    val customFields = LocalCustomColors.current
    var username by remember { mutableStateOf("") }
    val isLoading by authViewModel.isLoading.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        authViewModel.uiEvent.collect { event ->
            if(event is UiEvent.ShowSnackBar) { snackBarHostState.showSnackbar(event.message) }
            else if(event is UiEvent.NavigateToSecretPinScreen) {
                navController.navigate(Screens.Auth.SecretPinScreen) {
                    popUpTo(route = Screens.Auth.UsernameScreen) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { data ->
                Snackbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = customFields.largePadding,
                            vertical = customFields.smallPadding
                        ),
                    containerColor = customFields.snackBarBg,
                    contentColor = customFields.snackBarTextColor,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = data.visuals.message,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = AppConstants.SNACK_BAR_MAX_LINES
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = customFields.extraLargePadding)
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onBackground)
                        .padding(customFields.midPadding)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.skill_link_icon_transparent_bg),
                        contentDescription = "App Icon"
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            top = customFields.extraLargePadding,
                            bottom = customFields.midPadding,
                            start = customFields.midPadding,
                            end = customFields.midPadding
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Add Username",
                        style = MaterialTheme.typography.headlineLarge,
                        color = customFields.primaryTextColor
                    )

                    Spacer(modifier = Modifier.height(customFields.largeSpacing))

                    Text(
                        text = "The Username is visible to others.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = customFields.secondaryTextColor,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(customFields.smallSpacing))

                    Text(
                        text = "Once set, it cannot be changed",
                        style = MaterialTheme.typography.bodyMedium,
                        color = customFields.secondaryTextColor,
                        fontStyle = FontStyle.Italic
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            top = customFields.extraLargePadding,
                            bottom = customFields.midPadding,
                            start = customFields.midPadding,
                            end = customFields.midPadding
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StyledInputField(
                        value = username,
                        onValueChange = { username = it },
                        label = "Username",
                        isPassword = false,
                        leadingIcon = Icons.Default.AccountCircle,
                        customFields = customFields
                    )

                    Spacer(modifier = Modifier.height(customFields.extraLargeSpacing))

                    GradientButton(
                        text = "Add",
                        customFields = customFields,
                        isLoading = isLoading
                    ) {
                        if(username.isNotBlank()) {
                            authViewModel.setUsername(
                                username = username,
                                appStoreViewModel = appStoreViewModel,
                                userPrefsStoreViewModel = userPrefsStoreViewModel
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Dots(color = customFields.primaryUnfocusedColor, size = 8.dp)
                Spacer(modifier = Modifier.width(customFields.smallSpacing))
                Dots(color = customFields.primaryFocusedColor, size = 16.dp)
                Spacer(modifier = Modifier.width(customFields.smallSpacing))
                Dots(color = customFields.primaryUnfocusedColor, size = 8.dp)
            }
        }
    }
}