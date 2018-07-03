package ru.artempugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artempugachev.data.model.ProjectEntity

interface ProjectsDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

}