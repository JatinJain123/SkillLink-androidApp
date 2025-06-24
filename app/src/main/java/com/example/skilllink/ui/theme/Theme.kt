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
    val snackBarBg: Color,
    val snackBarTextColor: Color,

    /* padding values */
    val extraSmallPadding: Dp = 4.dp,
    val smallPadding: Dp = 8.dp,
    val midPadding: Dp = 12.dp,
    val largePadding: Dp = 16.dp,
    val extraLargePadding: Dp = 32.dp,

    /* spacing/margin values */
    val extraSmallSpacing: Dp = 4.dp,
    val smallSpacing: Dp = 8.dp,
    val midSpacing: Dp = 12.dp,
    val largeSpacing: Dp = 16.dp,
    val extraLargeSpacing: Dp = 32.dp
)

private val darkCustomFields = CustomFields(
    primaryTextColor = VeryLightGray,
    secondaryTextColor = Color.LightGray,
    inverseBg = Color.White,
    iconTint = Color.LightGray,
    primaryFocusedColor = Color.White,
    primaryUnfocusedColor = Color.LightGray,
    snackBarBg = darkGray,
    snackBarTextColor = Color.White
)

private val lightCustomFields = CustomFields(
    primaryTextColor = Color.Black,
    secondaryTextColor = Color.DarkGray,
    inverseBg = Color.Black,
    iconTint = Color.DarkGray,
    primaryFocusedColor = Color.Black,
    primaryUnfocusedColor = Color.DarkGray,
    snackBarBg = lightGray,
    snackBarTextColor = Color.Black
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

    val customFields = if(darkTheme) darkCustomFields else lightCustomFields

    CompositionLocalProvider(LocalCustomColors provides customFields) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}