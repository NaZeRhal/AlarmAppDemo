package com.maxrzhe.alarmappdemo.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.maxrzhe.alarmappdemo.NotificationHelper
import com.maxrzhe.alarmappdemo.worker.NotificationWorkRequestHelper.IS_SIMPLE

class NotificationWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        return try {
            val isSimple = inputData.getBoolean(IS_SIMPLE, false)
            if (isSimple) {
                NotificationHelper.showSimpleNotification(context)
            } else {
                NotificationHelper.showAlarm(context)
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}