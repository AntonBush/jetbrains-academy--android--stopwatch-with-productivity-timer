package org.hyperskill.stopwatch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class StopwatchViewModel(
    application: Application,
    private val stopwatch: Stopwatch
) : AndroidViewModel(application) {
    val time: LiveData<Long> = Transformations
        .map(stopwatch.getPassed()) { passed ->
            val limit = upperLimit.value
            if (limit != null) {
                when {
                    limit < passed
                            && upperLimitExceeded.value != true -> {
                        upperLimitExceeded.postValue(true)
                    }
                    passed <= limit
                            && upperLimitExceeded.value != false -> {
                        upperLimitExceeded.postValue(false)
                    }
                }
            }
            return@map passed
        }
    val upperLimit: MutableLiveData<Long?> by lazy {
        MutableLiveData(null)
    }
    private val upperLimitExceeded: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun isUpperLimitExceeded(): LiveData<Boolean> = upperLimitExceeded

    fun start() = stopwatch.start()

    fun reset() = stopwatch.reset()
}