package ru.artempugachev.mobileui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.artempugachev.mobileui.TrendHubApp
import ru.artempugachev.mobileui.di.module.*
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,
    DataModule::class,
    CacheModule::class,
    RemoteModule::class,
    AppModule::class,
    UiModule::class,
    PresentationModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TrendHubApp)
}