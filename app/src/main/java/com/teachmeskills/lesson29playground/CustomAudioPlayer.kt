package com.teachmeskills.lesson29playground

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

class CustomAudioPlayer(context: Context) {
    private val mediaPlayer: MediaPlayer = MediaPlayer()

    init {
        try {
            context.assets.openFd("example.mp3").use { afd ->
                mediaPlayer.setDataSource(afd.fileDescriptor)
                mediaPlayer.prepare()
            }
        } catch (e: IOException) {
            Log.i("ttt", e.message.orEmpty())
            e.printStackTrace()
        }
    }

    fun play() {
        mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun release() {
        mediaPlayer.release()
    }
}