package com.example.skilllink.ui.screens.commonComponents.exoPlayer

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.skilllink.domain.model.remote.Reel

class ExoPlayerPool(
    private val context: Context,
    reels: List<Reel>
) {
    private val players = hashMapOf<Int, ExoPlayer>()

    init { prepareExoPlayers(reels) }

    private fun prepareExoPlayers(pages: List<Reel>) {
        pages.onEachIndexed { index, _ ->
            createAndGet(index)
        }
    }

    fun createAndGet(index: Int): ExoPlayer {
        val id = index % PLAYERS_COUNT
        if(players.contains(id)) {
            return players[id]!!
        }

        val exoPlayer = ExoPlayer.Builder(context)
            .build()
            .apply {
                repeatMode = Player.REPEAT_MODE_ALL
                volume = 0f
            }
        players[id] = exoPlayer
        return exoPlayer
    }

    fun releaseAll() {
        players.forEach {
            it.value.stop()
            it.value.release()
        }
        players.clear()
    }

    fun updateVolume(volume: Float) {
        players.forEach {
            it.value.volume = volume
        }
    }

    companion object { private const val PLAYERS_COUNT = 3 }
}