package com.example.skilllink.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    background = Color.Black,
    onBackground = Color.White
)

private val LightColorScheme = lightColorScheme(
    background = Color.White,
    onBackground = Color.Black
)

data class CustomFields(
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val inverseBg: Color,
    val iconTint: Color,
    val primaryFocusedColor: Color,
    val primaryUnfocusedColor: Color,
    val smallPadding: Dp = 8.dp,
    val midPadding: Dp = 12.dp,
    val largePadding: Dp = 16.dp,
    val extraLargePadding: Dp = 32.dp,
    val smallSpacing: Dp = 8.dp,
    val midSpacing: Dp = 12.dp,
    val largeSpacing: Dp = 16.dp,
    val extraLargeSpacing: Dp = 32.dp
)

private val darkCustomColor = CustomFields(
    primaryTextColor = VeryLightGray,
    secondaryTextColor = Color.LightGray,
    inverseBg = Color.White,
    iconTint = Color.LightGray,
    primaryFocusedColor = Color.White,
    primaryUnfocusedColor = Color.LightGray
)

private val lightCustomColor = CustomFields(
    primaryTextColor = Color.Black,
    secondaryTextColor = Color.DarkGray,
    inverseBg = Color.Black,
    iconTint = Color.DarkGray,
    primaryFocusedColor = Color.Black,
    primaryUnfocusedColor = Color.DarkGray
)

val LocalCustomColors = staticCompositionLocalOf<CustomFields> {
    error("No CustomColors provided")
}

@Composable
fun SkillLinkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val customColor = if(darkTheme) darkCustomColor else lightCustomColor

    CompositionLocalProvider(LocalCustomColors provides customColor) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}