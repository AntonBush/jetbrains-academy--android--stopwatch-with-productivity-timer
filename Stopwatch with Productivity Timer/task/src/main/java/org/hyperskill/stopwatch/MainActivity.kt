package org.hyperskill.stopwatch

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random: Random = Random()
    private var defaultTextColor: Int? = null
    private lateinit var stopwatchViewModel: StopwatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatchViewModel = ViewModelProvider(
            this, StopwatchViewModelFactory(application)
        )[StopwatchViewModel::class.java]
        stopwatchViewModel.time.observe(this, ::updateTimer)
        stopwatchViewModel.isUpperLimitExceeded().observe(this, ::limitExceeded)

        startButton.setOnClickListener {
            stopwatchViewModel.start()
            progressBar.visibility = View.VISIBLE
            settingsButton.isEnabled = false
        }
        resetButton.setOnClickListener {
            stopwatchViewModel.reset()
            progressBar.visibility = View.INVISIBLE
            settingsButton.isEnabled = true
        }
        settingsButton.setOnClickListener {
            UpperLimitFragmentDialog(stopwatchViewModel.upperLimit)
                .show(supportFragmentManager, "upperLimit")
        }

        defaultTextColor = textView.currentTextColor
    }

    override fun onStop() {
        stopwatchViewModel.reset()
        super.onStop()
    }

    private fun updateTimer(passed: Long) {
        val passedSeconds = passed / 1000L
        val seconds = passedSeconds % 60L
        val minutes = passedSeconds / 60L
        val stringMinutes = formatter.format(minutes % 100)
        val stringSeconds = formatter.format(seconds % 100)
        textView.text = ("$stringMinutes:$stringSeconds")
        progressBar.indeterminateTintList = ColorStateList.valueOf(random.nextInt())
    }

    private fun limitExceeded(isLimitExceeded: Boolean) {
        if (isLimitExceeded) {
            textView.setTextColor(Color.RED)
            notifyLimitExceeded()
        } else {
            textView.setTextColor(defaultTextColor ?: Color.GRAY)
        }
    }

    private fun notifyLimitExceeded() {
        Log.d("NOTIFICATION", "ENTRY")
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Stopwatch status"
            val descriptionText = "Your stopwatch status"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = descriptionText

            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notification")
            .setContentText("Time exceeded")
            .setPriority(NotificationCompat.PRIORITY_MAX)

        notificationManager.notify(TIME_EXCEEDED_NOTIFICATION_ID, notificationBuilder.build())
    }

    companion object {
        private const val TIME_EXCEEDED_NOTIFICATION_ID = 393939
        private const val CHANNEL_ID = "org.hyperskill"
        private const val formatter = "%02d"
    }
}