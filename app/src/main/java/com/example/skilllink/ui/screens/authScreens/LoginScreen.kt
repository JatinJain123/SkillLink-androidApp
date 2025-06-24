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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllink.R
import com.example.skilllink.domain.model.local.UiEvent
import com.example.skilllink.ui.navigation.Screens
import com.example.skilllink.ui.reusableComponents.Dots
import com.example.skilllink.ui.reusableComponents.buttons.GradientButton
import com.example.skilllink.ui.reusableComponents.inputFields.StyledInputField
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.ui.theme.LocalCustomColors
import com.example.skilllink.ui.viewModels.AppStoreViewModel
import com.example.skilllink.ui.viewModels.AuthViewModel
import com.example.skilllink.utils.AppConstants

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    appStoreViewModel: AppStoreViewModel,
    navController: NavController
) {
    val customFields = LocalCustomColors.current
    var signup by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        authViewModel.uiEvent.collect { event ->
            if(event is UiEvent.ShowSnackBar) { snackBarHostState.showSnackbar(event.message) }
            else if(event is UiEvent.NavigateToUsernameScreen) {
                navController.navigate(Screens.Auth.UsernameScreen) {
                    popUpTo(route = Screens.Auth.LoginScreen) { inclusive = true }
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
                        text = "Create Account",
                        style = MaterialTheme.typography.headlineLarge,
                        color = customFields.primaryTextColor
                    )

                    Spacer(modifier= Modifier.height(customFields.largeSpacing))

                    Text(
                        text = "create a new account or login with an",
                        style = MaterialTheme.typography.bodyMedium,
                        color = customFields.secondaryTextColor,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier= Modifier.height(customFields.smallSpacing))

                    Text(
                        text = "existing one",
                        style = MaterialTheme.typography.bodyMedium,
                        color = customFields.secondaryTextColor,
                        fontStyle = FontStyle.Italic
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = customFields.extraLargePadding,
                            start = customFields.largePadding,
                            end = customFields.largePadding,
                            bottom = customFields.largePadding
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = { signup = true },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors (
                                    containerColor = if(signup) customFields.inverseBg else MaterialTheme.colorScheme.background,
                                    contentColor = if(signup) MaterialTheme.colorScheme.background else customFields.inverseBg,
                                )
                            ) {
                                Text(
                                    text = "SignUp",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }

                            Button(
                                onClick = { signup = false },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if(!signup) customFields.inverseBg else MaterialTheme.colorScheme.background,
                                    contentColor = if(!signup) MaterialTheme.colorScheme.background else customFields.inverseBg,
                                )
                            ) {
                                Text(
                                    text = "Login",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }

                        if(signup) {
                            SignUp(
                                customFields = customFields,
                                authViewModel = authViewModel,
                                appStoreViewModel = appStoreViewModel
                            )
                        } else {
                            Login(
                                customFields = customFields,
                                authViewModel = authViewModel,
                                appStoreViewModel = appStoreViewModel
                            )
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
                        Dots(color = customFields.primaryFocusedColor, size = 16.dp)
                        Spacer(modifier = Modifier.width(customFields.smallSpacing))
                        Dots(color = customFields.primaryUnfocusedColor, size = 8.dp)
                        Spacer(modifier = Modifier.width(customFields.smallSpacing))
                        Dots(color = customFields.primaryUnfocusedColor, size = 8.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun SignUp(
    customFields: CustomFields,
    authViewModel: AuthViewModel,
    appStoreViewModel: AppStoreViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val isLoading by authViewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = customFields.extraLargePadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StyledInputField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            isPassword = false,
            leadingIcon = Icons.Default.AccountCircle,
            customFields = customFields
        )

        Spacer(modifier = Modifier.height(customFields.midSpacing))

        StyledInputField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            leadingIcon = ImageVector.vectorResource(R.drawable.lock_icon),
            customFields = customFields
        )

        Spacer(modifier = Modifier.height(customFields.midSpacing))

        StyledInputField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            isPassword = true,
            leadingIcon = ImageVector.vectorResource(R.drawable.lock_icon),
            customFields = customFields,
            isError = password != confirmPassword
        )

        Spacer(modifier = Modifier.height(customFields.extraLargeSpacing))

        GradientButton(
            text = "SignUp",
            customFields = customFields,
            isLoading = isLoading
        ) {
            if(password == confirmPassword && password.isNotBlank() && email.isNotBlank()) {
                authViewModel.signup(
                    email = email,
                    password = password,
                    appStoreViewModel = appStoreViewModel
                )
            }
        }
    }
}

@Composable
fun Login(
    customFields: CustomFields,
    authViewModel: AuthViewModel,
    appStoreViewModel: AppStoreViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading by authViewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = customFields.extraLargePadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StyledInputField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            isPassword = false,
            leadingIcon = Icons.Default.AccountCircle,
            customFields = customFields
        )

        Spacer(modifier = Modifier.height(customFields.midSpacing))

        StyledInputField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            leadingIcon = ImageVector.vectorResource(R.drawable.lock_icon),
            customFields = customFields
        )

        Spacer(modifier = Modifier.height(customFields.extraLargeSpacing))

        GradientButton(
            text = "Login",
            customFields = customFields,
            isLoading = isLoading
        ) {
            if(password.isNotBlank() && email.isNotBlank()) {
                authViewModel.login(
                    email = email,
                    password = password,
                    appStoreViewModel = appStoreViewModel
                )
            }
        }
    }
}