package com.example.skilllink.ui.screens.learnerScreens.home

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.skilllink.ui.theme.CustomFields

@Composable
fun TrendingScroll(
    customFields: CustomFields
) {
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
    }
}