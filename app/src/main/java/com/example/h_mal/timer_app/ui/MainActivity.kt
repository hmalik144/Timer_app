package com.example.h_mal.timer_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.h_mal.timer_app.R
import com.example.h_mal.timer_app.ui.timer.TimerViewModel
import com.example.h_mal.timer_app.ui.timer.TimerViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    // retrieve the viewmodel factory from the kodein dependency injection
    override val kodein by kodein()
    private val factory by instance<TimerViewModelFactory>()
    lateinit var viewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(viewModelStore, factory).get(TimerViewModel::class.java)

        viewModel.timerText.observe(this, Observer {
            text_time.text = it
        })
    }


    /*
     * When activity is paused the timer is stopped
     * we resume the timer when the activity is resumed
     */
    override fun onResume() {
        super.onResume()
        viewModel.startTimer()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopTimer()
    }
}