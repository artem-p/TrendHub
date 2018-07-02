package ru.artempugachev.data.repository

import io.reactivex.Observable
import ru.artempugachev.data.model.ProjectEntity

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>

}