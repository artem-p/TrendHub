package ru.artempugachev.domain.interactor.browse

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

class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    /**
     * Test getProjects can completes
     * */
    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(3)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(3)
        stubGetProjects(Observable.just(projects))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }


    fun stubGetProjects(stubProjects: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects())
                .thenReturn(stubProjects)
    }
}