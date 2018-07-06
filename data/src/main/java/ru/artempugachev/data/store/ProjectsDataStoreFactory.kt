package ru.artempugachev.data.store

import ru.artempugachev.data.repository.ProjectsDataStore
import javax.inject.Inject

open class ProjectsDataStoreFactory @Inject constructor(
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    open fun getDataStore(areProjectsCached: Boolean,
                          isCacheExpired: Boolean): ProjectsDataStore {

        return if (areProjectsCached && !isCacheExpired) {
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }


    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }
}