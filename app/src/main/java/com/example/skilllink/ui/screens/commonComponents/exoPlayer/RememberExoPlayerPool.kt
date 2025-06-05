package com.example.skilllink.ui.screens.commonComponents.exoPlayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.skilllink.domain.model.remote.Reel

@Composable
fun rememberExoPlayerPool(
    reels: List<Reel>,
    mikeOn: Boolean
): ExoPlayerPool {
    val context = LocalContext.current
    val playersPool = remember { ExoPlayerPool(context = context, reels = reels) }

    LaunchedEffect(mikeOn) {
        playersPool.updateVolume(
            volume = if(mikeOn) 1f else 0f
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            playersPool.releaseAll()
        }
    }

    return playersPool
}