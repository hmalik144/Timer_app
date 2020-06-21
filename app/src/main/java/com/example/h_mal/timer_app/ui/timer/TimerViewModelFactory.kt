package com.example.h_mal.timer_app.ui.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.h_mal.timer_app.data.prefs.Preferences


/**
 * ViewModel provider factory to instantiate [TimerViewModel].
 * Required given MainViewModel has a non-empty constructor
 */
class TimerViewModelFactory(
    private val prefs: Preferences
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            return (TimerViewModel(prefs)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}