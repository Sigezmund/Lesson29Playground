package com.teachmeskills.lesson29playground

import androidx.lifecycle.ViewModel

class MainViewModel(private val player: CustomAudioPlayer) : ViewModel() {
    fun play() {
        player.play()
    }

    fun pause() {
        player.pause()
    }

    override fun onCleared() {
        player.release()
    }
}