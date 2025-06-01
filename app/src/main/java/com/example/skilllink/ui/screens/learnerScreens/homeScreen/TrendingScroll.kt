package com.example.skilllink.ui.screens.learnerScreens.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
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
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = customFields.extraLargePadding,
                start = customFields.midPadding,
                end = customFields.midPadding
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Trending",
            style = MaterialTheme.typography.headlineLarge,
            color = customFields.primaryTextColor,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(customFields.extraSmallSpacing))

        Text(
            text = "Top picks of the moment",
            style = MaterialTheme.typography.headlineSmall,
            color = customFields.secondaryTextColor,
            fontWeight = FontWeight.Normal
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = customFields.midPadding)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val testReelsList = testReelData.reels
            if(testReelsList.isEmpty()) {
                Text(
                    text = "No Updates Yet !",
                    color = customFields.secondaryTextColor,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            } else {
                testReelsList.forEachIndexed { _, reel ->
                    key(reel.id) {

                    }
                }
            }
        }
    }
}