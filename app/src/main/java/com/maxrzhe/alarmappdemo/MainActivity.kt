package com.maxrzhe.alarmappdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.WorkManager
import com.maxrzhe.alarmappdemo.worker.NotificationWorkRequestHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv_text)

        findViewById<Button>(R.id.btn_next_alarm).setOnClickListener {
            val timeInMillis = 10000L
            textView.text = "You will receive alarm message in ${timeInMillis / 1000} sec"
            val work = NotificationWorkRequestHelper.enqueueWork(
                context = this,
                isSimple = false,
                timeInMillis = timeInMillis
            )

        }

        findViewById<Button>(R.id.btn_next_simple).setOnClickListener {
            val timeInMillis = 8000L
            textView.text = "You will receive simple message in ${timeInMillis / 1000} sec"
            val work = NotificationWorkRequestHelper.enqueueWork(
                context = this,
                isSimple = true,
                timeInMillis = 8000L
            )
        }
    }
}