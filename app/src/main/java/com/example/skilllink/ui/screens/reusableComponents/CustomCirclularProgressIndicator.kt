package com.example.skilllink.ui.screens.reusableComponents

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.skilllink.ui.theme.VeryLightGray

@Composable
fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 60.dp,
    strokeWidth: Dp = 4.dp,
    showPercentage: Boolean = false,
    progress: Float? = null,
    icon: ImageVector = Icons.Default.PlayArrow
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ),
        label = "spin"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(indicatorSize + 6.dp)
            .shadow(1.dp, CircleShape, clip = false)
            .background(MaterialTheme.colorScheme.background, CircleShape)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(
                color = VeryLightGray.copy(alpha = 0.2f),
                style = Stroke(width = strokeWidth.toPx())
            )
        }

        if (progress == null) {
            CircularProgressIndicator(
                color = VeryLightGray,
                strokeWidth = strokeWidth,
                modifier = Modifier
                    .size(indicatorSize)
                    .rotate(angle)
            )
        } else {
            CircularProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier.size(indicatorSize),
                color = VeryLightGray,
                strokeWidth = strokeWidth,
                trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
            )
        }

        if (showPercentage && progress != null) {
            Text(
                text = "${(progress * 100).toInt()}%",
                color = Color.White,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
        } else if (!showPercentage) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = VeryLightGray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

