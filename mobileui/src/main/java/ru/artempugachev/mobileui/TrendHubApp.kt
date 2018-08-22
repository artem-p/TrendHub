package ru.artempugachev.mobileui

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import ru.artempugachev.mobileui.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class TrendHubApp : Application(), HasActivityInjector {
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()

        setUpTimber()

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }


    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }
}