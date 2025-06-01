package com.example.skilllink.ui.reusableComponents.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.ui.theme.LightNavyBlue
import com.example.skilllink.ui.theme.NavyBlue

@Composable
fun GradientButton(
    text: String,
    customFields: CustomFields,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(LightNavyBlue, NavyBlue)
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if(isLoading) {
            CircularProgressIndicator(
                color = customFields.primaryTextColor,
                strokeWidth = 2.dp,
                modifier = Modifier
                    .padding(vertical = customFields.midPadding)
                    .size(20.dp)
            )
        } else {
            Text(
                text = text,
                color = customFields.primaryTextColor,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(
                        top = customFields.midPadding,
                        bottom = customFields.midPadding
                    )
            )
        }
    }
}