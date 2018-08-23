package ru.artempugachev.mobileui.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module


@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context
}