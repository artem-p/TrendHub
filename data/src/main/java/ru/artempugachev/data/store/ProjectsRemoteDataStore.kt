package ru.artempugachev.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artempugachev.data.model.ProjectEntity
import ru.artempugachev.data.repository.ProjectsDataStore
import ru.artempugachev.data.repository.ProjectsRemote
import javax.inject.Inject

class ProjectsRemoteDataStore @Inject constructor(private val projectsRemote: ProjectsRemote): ProjectsDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }


    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here...")
    }


    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here...")

    }


    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Getting bookmarked projects isn't supported here...")
    }


    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Settings bookmarks isn't supported here...")
    }


    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Settings bookmarks isn't supported here...")
    }

}