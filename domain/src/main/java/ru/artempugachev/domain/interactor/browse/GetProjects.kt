package ru.artempugachev.domain.interactor.browse

import io.reactivex.Observable
import ru.artempugachev.domain.executor.PostExecutionThread
import ru.artempugachev.domain.interactor.ObservableUseCase
import ru.artempugachev.domain.model.Project
import ru.artempugachev.domain.repository.ProjectsRepository
import javax.inject.Inject

class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}