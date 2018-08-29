package ru.artempugachev.mobileui.di.module

import dagger.Binds
import dagger.Module
import ru.artempugachev.data.ProjectsDataRepository
import ru.artempugachev.domain.repository.ProjectsRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}