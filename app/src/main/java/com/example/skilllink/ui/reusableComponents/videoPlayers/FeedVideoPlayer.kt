package com.example.skilllink.ui.reusableComponents.videoPlayers

import android.graphics.Color
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView


@OptIn(UnstableApi::class)
@Composable
fun FeedVideoPlayer(
    exoPlayer: ExoPlayer,
    videoPath: String,
    isPlaying: Boolean
) {
    val context = LocalContext.current
    val playerView = remember {
        exoPlayer.apply {
            setMediaItem(MediaItem.fromUri(videoPath), true)
            prepare()
        }
        PlayerView(context).apply {
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
            useController = false
            player = exoPlayer
            setShutterBackgroundColor(Color.BLACK)
        }
    }
    AndroidView(factory = { playerView })

    exoPlayer.playWhenReady = isPlaying
}