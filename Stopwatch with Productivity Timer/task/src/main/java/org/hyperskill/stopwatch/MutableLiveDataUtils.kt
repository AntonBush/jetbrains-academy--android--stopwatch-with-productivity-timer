package org.hyperskill.stopwatch

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData(initValue: T): MutableLiveData<T> {
    val mutableLiveData = MutableLiveData<T>()
    mutableLiveData.postValue(initValue)
    return mutableLiveData
}