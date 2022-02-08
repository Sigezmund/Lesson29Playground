package com.teachmeskills.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class BootCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("ttt", intent.toString())
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {

//            Также вместо WorkManager можно использовать startForegraoundService
            WorkManager.getInstance(context)
                .enqueue(OneTimeWorkRequestBuilder<BackgroundWork>().build())
        }
    }
}