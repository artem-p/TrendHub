package ru.artempugachev.mobileui.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.artempugachev.cache.ProjectsCacheImpl
import ru.artempugachev.cache.db.ProjectsDatabase
import ru.artempugachev.data.repository.ProjectsCache

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}