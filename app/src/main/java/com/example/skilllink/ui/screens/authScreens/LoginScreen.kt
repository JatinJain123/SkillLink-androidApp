package com.example.skilllink.ui.screens.authScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.skilllink.ui.navigation.Screens
import com.example.skilllink.ui.screens.reusableComponents.Dots
import com.example.skilllink.ui.screens.reusableComponents.buttons.GradientButton
import com.example.skilllink.ui.screens.reusableComponents.inputFields.StyledInputField
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.ui.theme.LocalCustomColors

@Composable
fun LoginScreen(
    navController: NavController
) {
    val customFields = LocalCustomColors.current
    var signup by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(WindowInsets.systemBars.asPaddingValues())
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
                        SignUp(customFields, navController)
                    } else {
                        Login(customFields, navController)
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

@Composable
fun SignUp(
    customFields: CustomFields,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
            isLoading = false
        ) {
            navController.navigate(Screens.AuthScreens.UserNameScreen)
        }
    }
}

@Composable
fun Login(
    customFields: CustomFields,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            isLoading = false
        ) {
            navController.navigate(Screens.AuthScreens.UserNameScreen)
        }
    }
}