package com.teachmeskills.lesson29playground

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.teachmeskills.lesson29playground.databinding.ActivityMainBinding
import com.teachmeskills.lesson29playground.extensions.getViewModel
import com.teachmeskills.lesson29playground.services.AudioService


class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { getViewModel { MainViewModel(CustomAudioPlayer(this)) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.play.setOnClickListener {
//            viewModel.play()
            this.startService(AudioService.start(this))
        }
        binding.pause.setOnClickListener {
//            viewModel.pause()
            this.startService(AudioService.pause(this))

        }



        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    }

}