package com.example.skilllink.ui.screens.learnerScreens.homeScreen

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.skilllink.R
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.ui.theme.LightNavyBlue

@Composable
fun Header(
    userName: String,
    profilePic: String,
    customFields: CustomFields,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = customFields.largePadding,
                start = customFields.midPadding,
                end = customFields.midPadding,
                bottom = customFields.midPadding
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.20f)
                    .aspectRatio(1f)
                    .border(
                        width = 2.dp,
                        color = LightNavyBlue,
                        shape = CircleShape
                    )
            ) {
                if(profilePic.isNotEmpty()) {
                    AsyncImage(
                        model = Uri.parse(profilePic),
                        contentDescription = "profile Picture",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = customFields.iconTint,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.width(customFields.midSpacing))

            Column(
                modifier = Modifier
                    .weight(0.75f)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Welcome ! $userName",
                    style = MaterialTheme.typography.bodyLarge,
                    color = customFields.primaryTextColor
                )

                Spacer(modifier = Modifier.height(customFields.smallSpacing))

                Text(
                    text = "learn. grow. create",
                    style = MaterialTheme.typography.bodyMedium,
                    color = customFields.secondaryTextColor,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        Row(
            modifier = Modifier
                .weight(0.25f)
                .wrapContentHeight()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                Icon(
                    painter = painterResource(R.drawable.chat_icon),
                    contentDescription = null,
                    tint = customFields.iconTint,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {

                        }
                )
            }

            Spacer(modifier = Modifier.width(customFields.smallSpacing))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = customFields.iconTint,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {

                        }
                )
            }
        }
    }
}