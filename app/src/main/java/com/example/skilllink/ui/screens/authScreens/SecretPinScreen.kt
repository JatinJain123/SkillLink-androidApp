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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllink.R
import com.example.skilllink.ui.navigation.Screens
import com.example.skilllink.ui.reusableComponents.Dots
import com.example.skilllink.ui.reusableComponents.buttons.GradientButton
import com.example.skilllink.ui.reusableComponents.inputFields.OtpInputField
import com.example.skilllink.ui.theme.LocalCustomColors

@Composable
fun SecretPinScreen(
    navController: NavController
) {
    val customFields = LocalCustomColors.current
    var spin by remember { mutableStateOf("") }

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
                    text = "Create SPin",
                    style = MaterialTheme.typography.headlineLarge,
                    color = customFields.primaryTextColor
                )

                Spacer(modifier = Modifier.height(customFields.largeSpacing))

                Text(
                    text = "The Secret Pin is important to maintain",
                    style = MaterialTheme.typography.bodyMedium,
                    color = customFields.secondaryTextColor,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.height(customFields.smallSpacing))

                Text(
                    text = "security and privacy",
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
                OtpInputField(
                    customFields = customFields
                ) { otp -> spin = otp }

                Spacer(modifier = Modifier.height(customFields.extraLargeSpacing))

                GradientButton(
                    text = "Set",
                    customFields = customFields,
                    isLoading = false
                ) {
                    navController.navigate(Screens.Home.HomeScreen) {
                        popUpTo(route = Screens.Auth.LoginScreen) {
                            inclusive = true
                        }
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
            Dots(color = customFields.primaryUnfocusedColor, size = 8.dp)
            Spacer(modifier = Modifier.width(customFields.smallSpacing))
            Dots(color = customFields.primaryFocusedColor, size = 16.dp)
        }
    }
}