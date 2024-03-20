package com.example.a3200midterm.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

class NotificationsViewModel : ViewModel() {
    private var timer: Timer? = null
    private val _timerValue = MutableLiveData<Long>()
    val timerValue: LiveData<Long>
        get() = _timerValue

    init {
        _timerValue.value = 0
    }

    fun startTimer() {
        timer = Timer().apply {
            scheduleAtFixedRate(0, 1000) {
                _timerValue.postValue(_timerValue.value!! + 1)
            }
        }
    }

    fun pauseTimer() {
        timer?.cancel()
        timer = null
    }

    fun resetTimer() {
        pauseTimer()
        _timerValue.postValue(0)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}