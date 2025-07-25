package com.example.skilllink.ui.reusableComponents

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skilllink.R
import com.example.skilllink.ui.theme.SkillLinkTheme
import com.example.skilllink.ui.theme.VeryLightGray
import kotlinx.coroutines.delay

data class UseStates(
    var showLogo: Boolean = false,
    var showName: Boolean = false,
    var showTagline: Boolean = false,
    var loading: Boolean = false
)

private object SplashConstants {
    const val AFTER_LOGO_DUR: Long = 200
    const val AFTER_NAME_DUR: Long = 200
    const val AFTER_TAG_DUR: Long = 400
    const val LOGO_ANIM_DUR = 800
    const val NAME_ANIM_DUR = 600
    const val TAG_ANIM_DUR = 600
}

@Composable
fun SplashScreen() {
    val states = remember { mutableStateOf(UseStates()) }

    LaunchedEffect(Unit) {
        states.value = states.value.copy(showLogo = true)
        delay(SplashConstants.AFTER_LOGO_DUR)
        states.value = states.value.copy(showName = true)
        delay(SplashConstants.AFTER_NAME_DUR)
        states.value = states.value.copy(showTagline = true)
        delay(SplashConstants.AFTER_TAG_DUR)
        states.value = states.value.copy(loading = true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Logo(visible = states.value.showLogo)
            Spacer(modifier = Modifier.height(16.dp))
            AppName(visible = states.value.showName)
            Spacer(modifier = Modifier.height(12.dp))
            Tagline(visible = states.value.showTagline)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .align(Alignment.BottomCenter)
        ) {
            ShowLoading(visible = states.value.loading)
        }
    }
}

@Composable
fun Logo(visible: Boolean) {
    val logoAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(SplashConstants.LOGO_ANIM_DUR)
    )
    val logoOffset by animateDpAsState(
        targetValue = if (visible) 0.dp else (-50).dp,
        animationSpec = tween(SplashConstants.LOGO_ANIM_DUR)
    )

    Box(
        modifier = Modifier
            .alpha(logoAlpha)
            .offset(y = logoOffset)
    ) {
        Image(
            painter = painterResource(id = R.drawable.skill_link_logo_transparent_bg),
            contentDescription = "App Logo",
            modifier = Modifier.size(320.dp)
        )
    }
}

@Composable
fun AppName(visible: Boolean) {
    val nameAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(SplashConstants.NAME_ANIM_DUR)
    )
    val nameOffset by animateDpAsState(
        targetValue = if (visible) 0.dp else 40.dp,
        animationSpec = tween(SplashConstants.NAME_ANIM_DUR)
    )

    Box(
        modifier = Modifier
            .alpha(nameAlpha)
            .offset(y = nameOffset)
    ) {
        Text(
            text = "SkillLink",
            fontSize = 48.sp,
            fontFamily = FontFamily(Font(R.font.ibm_plex_serif_bold)),
            color = VeryLightGray,
        )
    }
}

@Composable
fun Tagline(visible: Boolean) {
    val taglineAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(SplashConstants.TAG_ANIM_DUR)
    )
    val taglineOffset by animateDpAsState(
        targetValue = if (visible) 0.dp else 20.dp,
        animationSpec = tween(SplashConstants.TAG_ANIM_DUR)
    )

    Box(
        modifier = Modifier
            .alpha(taglineAlpha)
            .offset(y = taglineOffset)
    ) {
        Text(
            text = "learn. grow. create.",
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(R.font.caveat_brush_font)),
            fontStyle = FontStyle.Italic,
            color = Color.LightGray
        )
    }
}

@Composable
fun ShowLoading(visible: Boolean) {
    if(visible) {
        SkillLinkTheme(darkTheme = true) {
            CustomCircularProgressIndicator()
        }
    }
}