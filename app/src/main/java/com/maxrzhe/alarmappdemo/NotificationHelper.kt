package com.maxrzhe.alarmappdemo

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.maxrzhe.alarmappdemo.MainApp.Companion.ALARM_CHANNEL_ID
import com.maxrzhe.alarmappdemo.MainApp.Companion.NOTIF_CHANNEL_ID
import com.maxrzhe.alarmappdemo.util.RandomUtil

object NotificationHelper {

    fun showAlarm(context: Context) {
        val pendingIntent = Intent(context, MainActivity::class.java).run {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            PendingIntent.getActivity(
                context,
                RandomUtil.getRandomInt(),
                this,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val notification = NotificationCompat.Builder(context, ALARM_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_info)
            .setContentTitle("ALARM notification")
            .setContentText("You received this ALARM notification")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(pendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(true)
            .build()


        /** if set, the audio will be repeated until the notification is cancelled or the notification window is opened. */
        notification.flags = Notification.FLAG_INSISTENT

        with(NotificationManagerCompat.from(context)) {
            notify(RandomUtil.getRandomInt(), notification)
        }

    }

    fun showSimpleNotification(context: Context) {
        val pendingIntent = Intent(context, MainActivity::class.java).run {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("isSoundRinging", true)
            PendingIntent.getActivity(
                context,
                RandomUtil.getRandomInt(),
                this,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val notification = NotificationCompat.Builder(context, NOTIF_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_info)
            .setContentTitle("Simple notification")
            .setContentText("You received this simple notification")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(pendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(RandomUtil.getRandomInt(), notification)
        }

    }
}