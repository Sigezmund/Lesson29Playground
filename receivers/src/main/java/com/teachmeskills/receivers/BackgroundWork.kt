package com.teachmeskills.receivers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class BackgroundWork(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
//        SOME BACKGROUND OPERATION
        return Result.success()
    }
}