package com.example.skilllink.ui.reusableComponents.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.skilllink.domain.model.remote.Reel
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.ui.theme.LightNavyBlue

@Composable
fun CircularReelsCard(
    reel: Reel,
    size: Dp,
    customFields: CustomFields,
    navigate: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = customFields.smallPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .border(
                    width = 4.dp,
                    color = LightNavyBlue,
                    shape = CircleShape
                )
        ) {
            Image(
                painter = rememberAsyncImagePainter(reel.thumbnailUrl),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxSize()
                    .clickable { navigate() }
            )
        }

        Spacer(modifier = Modifier.height(customFields.smallSpacing))

        Text(
            text = "test Name",
            color = customFields.primaryTextColor,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}