package com.maxrzhe.alarmappdemo.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object NotificationWorkRequestHelper {

    const val IS_SIMPLE = "is_simple"

    fun enqueueWork(context: Context, isSimple: Boolean, timeInMillis: Long): Operation {

        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(timeInMillis, TimeUnit.MILLISECONDS)
            .setInputData(workDataOf(IS_SIMPLE to isSimple))
            .build()

        return WorkManager.getInstance(context).enqueueUniqueWork(
            "notify",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}