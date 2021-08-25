package org.hyperskill.stopwatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*


class Stopwatch {
    private val timer: Timer = Timer(true)
    private var isRunning: Boolean = false
    private var stopwatchUpdateTask: TimerTask? = null
    val passed: MutableLiveData<Long> by lazy { MutableLiveData(0L) }

    fun getPassed(): LiveData<Long> = passed

    fun start() {
        if (isRunning) {
            return
        }
        isRunning = true

        val begin = Date()
        stopwatchUpdateTask = object : TimerTask() {
            override fun run() {
                passed.postValue(Date().time - begin.time)
            }
        }
        timer.schedule(stopwatchUpdateTask, 0, 1_000)
    }

    fun reset() {
        if (!isRunning) {
            return
        }

        stopwatchUpdateTask?.cancel()
        passed.postValue(0L)
        isRunning = false
    }
}