package com.example.h_mal.timer_app.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


const val TimerConstant = "TIMER_CONSTANT"
class Preferences(val context: Context){

    var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Synchronized
    fun loadCurrentTimeInPrefs(): Int {
        return preferences.getInt(TimerConstant, 0)
    }

    @Synchronized
    fun saveCurrentTimeInPrefs(time: Int) {
        preferences.edit().putInt(TimerConstant, time).apply()
    }
}