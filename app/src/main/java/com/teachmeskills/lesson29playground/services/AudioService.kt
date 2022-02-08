package com.teachmeskills.lesson29playground.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.teachmeskills.lesson29playground.CustomAudioPlayer
import com.teachmeskills.lesson29playground.MainActivity
import com.teachmeskills.lesson29playground.R

class AudioService : Service() {

    private val player: CustomAudioPlayer by lazy { CustomAudioPlayer(this) }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        when (intent.getStringExtra(EXTRA_KEY)) {
            EXTRA_PLAY -> play()
            EXTRA_PAUSE -> pause()
        }
        return START_STICKY
    }

    fun play() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(NOTIFICATION_ID, createNotification(), ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
        } else {
            startForeground(NOTIFICATION_ID, createNotification())
        }
        player.play()
    }

    fun pause() {
        stopForeground(false)
        player.pause()
    }

    private fun createNotification(): Notification {

        val pIntent = PendingIntent.getActivity(
            this,
            CONTENT_REQUEST_CODE,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music is playing")
            .setContentText("Super track")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSmallIcon(R.drawable.ic_baseline_audiotrack)
            .setAutoCancel(true)
            .setContentIntent(pIntent)
            .build()
        createChannel()
        return notification
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Audio channel"
            val descriptionText = "Audio channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    companion object {
        private const val EXTRA_KEY = "key"
        private const val EXTRA_PLAY = "play"
        private const val EXTRA_PAUSE = "pause"
        private const val NOTIFICATION_ID = 1
        private const val CONTENT_REQUEST_CODE = 2
        private const val CHANNEL_ID = "player"
        fun start(context: Context): Intent {
            return Intent(context, AudioService::class.java).putExtra(
                EXTRA_KEY, EXTRA_PLAY
            )
        }

        fun pause(context: Context): Intent {
            return Intent(context, AudioService::class.java).putExtra(
                EXTRA_KEY, EXTRA_PAUSE
            )
        }
    }
}