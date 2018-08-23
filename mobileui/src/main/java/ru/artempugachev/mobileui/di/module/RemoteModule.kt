package ru.artempugachev.mobileui.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.artempugachev.data.repository.ProjectsRemote
import ru.artempugachev.mobileui.BuildConfig
import ru.artempugachev.remote.ProjectsRemoteImpl
import ru.artempugachev.remote.service.GithubTrendingService
import ru.artempugachev.remote.service.GithubTrendingServiceFactory

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}