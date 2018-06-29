package ru.artempugachev.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artempugachev.domain.model.Project

interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>
}