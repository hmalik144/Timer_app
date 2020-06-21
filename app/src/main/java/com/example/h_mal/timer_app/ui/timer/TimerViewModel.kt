package com.example.h_mal.timer_app.ui.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.h_mal.timer_app.data.prefs.Preferences
import java.util.*

class TimerViewModel (
    private val prefs: Preferences
): ViewModel(){

    private var timer: Timer? = null
    private var ticks = 0

    val timerText = MutableLiveData<String>()

    private fun loadTimeFromPrefs(): Int = prefs.loadCurrentTimeInPrefs()

    private fun saveTimeToPrefs(secondsSince: Int){
        val total = loadTimeFromPrefs() + secondsSince
        prefs.saveCurrentTimeInPrefs(total)
    }

    fun startTimer(){
        // load previously saved time
        val startingTimer = loadTimeFromPrefs()
        // timer task running on 1 second intervals
        val timerTask = object : TimerTask() {
            override fun run() {
                ticks++
                val current = startingTimer + ticks
                timerText.postValue(current.convertSecondsToTimeString())
            }
        }
        timer = Timer()
        timer?.schedule(timerTask, 0, 1000)
    }

    fun stopTimer(){
        timer?.cancel()
        saveTimeToPrefs(ticks)
        ticks = 0
    }

    /*
     * Inline function that converts a time in milliseconds
     * eg. 72504 is 20:08:24
     */
    fun Int.convertSecondsToTimeString(): String{
        val hours = this / 3600;
        val minutes = (this % 3600) / 60;
        val seconds = this % 60;
        return String.format("%2d:%02d:%02d", hours, minutes, seconds);
    }
}