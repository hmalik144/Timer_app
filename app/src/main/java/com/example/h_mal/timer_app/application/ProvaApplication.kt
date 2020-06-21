package com.example.h_mal.timer_app.application

import android.app.Application
import com.example.h_mal.timer_app.data.prefs.Preferences
import com.example.h_mal.timer_app.ui.timer.TimerViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ApplicationClass : Application(), KodeinAware {

    // Kodein aware to initialise the classes used for DI
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ApplicationClass))
        /**
         * Singletons created for dependency injections
         * Providers created for viewmodel factories used for dependency injection
         * [instance] can used to inject an previously declared class or context
         * */
        bind() from singleton { Preferences(instance()) }
        bind() from provider { TimerViewModelFactory(instance()) }
    }

}