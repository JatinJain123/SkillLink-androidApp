package com.example.skilllink.ui.screens.learnerScreens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skilllink.ui.reusableComponents.cards.CircularReelsCard
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.utils.testReelData

@Composable
fun FollowersScroll(
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
            text = "Following",
            style = MaterialTheme.typography.headlineMedium,
            color = customFields.primaryTextColor,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(customFields.extraSmallSpacing))

        Text(
            text = "Updates from your network",
            style = MaterialTheme.typography.bodyMedium,
            color = customFields.secondaryTextColor,
            fontWeight = FontWeight.Normal
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = customFields.midPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(customFields.smallSpacing)
        ) {
            if(testReelsList.isEmpty()) {
                item {
                    Text(
                        text = "No Updates Yet !",
                        color = customFields.secondaryTextColor,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(testReelsList) { reel ->
                    CircularReelsCard(
                        reel = reel,
                        size = 90.dp,
                        customFields = customFields,
                        navigate = { }
                    )
                }
            }
        }
    }
}