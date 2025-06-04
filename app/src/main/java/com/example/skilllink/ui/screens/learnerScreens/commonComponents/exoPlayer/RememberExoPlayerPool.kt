package com.example.skilllink.ui.screens.learnerScreens.commonComponents.exoPlayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.skilllink.domain.model.remote.Reel

@Composable
fun rememberExoPlayerPool(reels: List<Reel>): ExoPlayerPool {
    val context = LocalContext.current
    val playersPool = remember { ExoPlayerPool(context, reels) }

    DisposableEffect(Unit) {
        onDispose {
            playersPool.releaseAll()
        }
    }

    return playersPool
}