package ru.artempugachev.domain.interactor.bookmark

import io.reactivex.Completable
import ru.artempugachev.domain.executor.PostExecutionThread
import ru.artempugachev.domain.interactor.CompletableUseCase
import ru.artempugachev.domain.repository.ProjectsRepository
import javax.inject.Inject


class UnbookmarkProject @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : CompletableUseCase<UnbookmarkProject.Params>(postExecutionThread){


    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return projectsRepository.unbookmarkProject(params.projectId)
    }


    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }

}