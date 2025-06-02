package com.example.skilllink.ui.screens.learnerScreens.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.utils.testReelData

@Composable
fun TrendingScroll(
    customFields: CustomFields,
    navController: NavController
) {
    val testReelsList = testReelData.reels

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = customFields.largePadding,
                start = customFields.midPadding,
                end = customFields.midPadding
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Trending",
            style = MaterialTheme.typography.headlineMedium,
            color = customFields.primaryTextColor,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(customFields.extraSmallSpacing))

        Text(
            text = "Top picks of the moment",
            style = MaterialTheme.typography.bodyMedium,
            color = customFields.secondaryTextColor,
            fontWeight = FontWeight.Normal
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = customFields.midPadding),
            verticalArrangement = Arrangement.spacedBy(customFields.midSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(testReelsList.isEmpty()) {
                Text(
                    text = "No Updates Yet !",
                    color = customFields.secondaryTextColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            } else {
                testReelsList.forEachIndexed { _, reel ->

                }
            }
        }
    }
}