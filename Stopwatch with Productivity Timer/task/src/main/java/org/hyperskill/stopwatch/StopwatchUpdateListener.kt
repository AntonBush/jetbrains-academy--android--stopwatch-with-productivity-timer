package org.hyperskill.stopwatch

interface StopwatchUpdateListener {
    fun onStopwatchUpdate(currentTime: Long)
}