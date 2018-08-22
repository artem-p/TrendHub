package ru.artempugachev.mobileui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.artempugachev.mobileui.TrendHubApp
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TrendHubApp)
}