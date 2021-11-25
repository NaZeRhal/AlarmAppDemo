package com.maxrzhe.alarmappdemo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri

class MainApp : Application() {

    companion object {
        const val ALARM_CHANNEL_ID = "demo_alarm_channel_id"
        const val NOTIF_CHANNEL_ID = "demo_notif_channel_id"
        const val TAG = "DBG"
    }

    override fun onCreate() {
        super.onCreate()
        createAlarmNotificationChannel()
        createNotificationChannel()
    }

    private fun createAlarmNotificationChannel() {
        val alarmSoundUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + applicationContext.packageName + "/" + R.raw.slow_spring_board
        )
        val audioAttrs = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
            .build()

        val name = getString(R.string.alarm_channel_name)
        val descText = getString(R.string.alarm_channel_desc)
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(ALARM_CHANNEL_ID, name, importance).apply {
            description = descText
            setSound(alarmSoundUri, audioAttrs)
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotificationChannel() {
        val name = getString(R.string.nofif_channel_name)
        val descText = getString(R.string.notif_channel_desc)
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(NOTIF_CHANNEL_ID, name, importance).apply {
            description = descText
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}