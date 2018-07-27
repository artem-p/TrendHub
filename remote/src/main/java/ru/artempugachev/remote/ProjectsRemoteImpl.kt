package ru.artempugachev.remote

import io.reactivex.Observable
import ru.artempugachev.data.model.ProjectEntity
import ru.artempugachev.data.repository.ProjectsRemote
import ru.artempugachev.remote.mapper.ProjectsResponseModelMapper
import ru.artempugachev.remote.service.GithubTrendingService
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectsResponseModelMapper
) : ProjectsRemote {


    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
                .map { it.items.map { mapper.mapFromModel(it) } }
    }
}