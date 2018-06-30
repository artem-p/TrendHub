package ru.artempugachev.domain.interactor.bookmark

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.artempugachev.domain.data.ProjectDataFactory
import ru.artempugachev.domain.executor.PostExecutionThread
import ru.artempugachev.domain.model.Project
import ru.artempugachev.domain.repository.ProjectsRepository

class GetBookmarkedProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread)
    }


    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(3)))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }


    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(3)
        stubGetProjects(Observable.just(projects))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }


    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getBookmarkedProjects())
                .thenReturn(observable)
    }
}