package ru.artempugachev.mobileui.di.module

import dagger.Binds
import dagger.Module
import ru.artempugachev.data.ProjectsDataRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}