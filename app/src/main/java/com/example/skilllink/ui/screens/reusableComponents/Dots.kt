package com.example.skilllink.ui.screens.reusableComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun Dots(
    color: Color,
    size: Dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(shape = CircleShape, color = color)
    )
}