package ru.artempugachev.data

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.artempugachev.data.mapper.ProjectMapper
import ru.artempugachev.data.model.ProjectEntity
import ru.artempugachev.data.repository.ProjectsCache
import ru.artempugachev.data.repository.ProjectsDataStore
import ru.artempugachev.data.store.ProjectsDataStoreFactory
import ru.artempugachev.data.test.factory.ProjectFactory
import ru.artempugachev.domain.model.Project


@RunWith(JUnit4::class)
class ProjectsDataRepositoryTest {

    private val mapper = mock<ProjectMapper>()
    private val factory = mock<ProjectsDataStoreFactory>()
    private val store = mock<ProjectsDataStore>()
    private val cache = mock<ProjectsCache>()
    private val repository = ProjectsDataRepository(mapper, cache, factory)


    @Before
    fun setUp() {
        stubFactoryGetDataStore()
    }


    @Test
    fun getProjectsComplete() {
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), any())

        val testObserver = repository.getProjects().test()
        testObserver.assertComplete()
    }


    private fun stubMapper(model: Project, entity: ProjectEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }


    private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getProjects())
                .thenReturn(observable)
    }


    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
                .thenReturn(store)
    }

}