package ru.artempugachev.mobileui.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.artempugachev.domain.executor.PostExecutionThread
import ru.artempugachev.mobileui.browse.BrowseActivity
import ru.artempugachev.mobileui.UiThread


@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity
}