package ru.artempugachev.data

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artempugachev.domain.model.Project
import ru.artempugachev.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(): ProjectsRepository {
    override fun getProjects(): Observable<List<Project>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bookmarkProject(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unbookmarkProject(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}