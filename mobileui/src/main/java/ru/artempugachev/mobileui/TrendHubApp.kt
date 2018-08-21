package ru.artempugachev.mobileui

import android.app.Application
import timber.log.Timber

class TrendHubApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setUpTimber()
    }


    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }
}