package com.teachmeskills.lesson29playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.teachmeskills.lesson29playground.databinding.ActivityMainBinding
import com.teachmeskills.lesson29playground.extensions.getViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { getViewModel { MainViewModel(CustomAudioPlayer(this)) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.play.setOnClickListener {
            viewModel.play()
        }
        binding.pause.setOnClickListener {
            viewModel.pause()
        }
    }

}