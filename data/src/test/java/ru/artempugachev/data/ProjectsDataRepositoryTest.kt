package ru.artempugachev.data

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
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
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreProjectsCached(Single.just(false))
        stubSaveProjects(Completable.complete())
    }


    @Test
    fun getProjectsComplete() {
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), any())

        val testObserver = repository.getProjects().test()
        testObserver.assertComplete()
    }


    @Test
    fun getProjectsReturnsData() {
        val projectEntity = ProjectFactory.makeProjectEntity()
        val project = ProjectFactory.makeProject()

        stubGetProjects(Observable.just(listOf(projectEntity)))
        stubMapper(project, projectEntity)

        val testObserver = repository.getProjects().test()
        testObserver.assertValue(listOf(project))
    }


    @Test
    fun getBookmarkedProjectsComplete() {
        stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), any())

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }


    private fun stubIsCacheExpired(isExpired: Single<Boolean>) {
        whenever(cache.isProjectsCacheExpired())
                .thenReturn(isExpired)
    }


    private fun stubAreProjectsCached(areProjectsCached: Single<Boolean>) {
        whenever(cache.areProjectsCached())
                .thenReturn(areProjectsCached)
    }


    private fun stubMapper(model: Project, entity: ProjectEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }


    private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getProjects())
                .thenReturn(observable)
    }

    private fun stubGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getBookmarkedProjects())
                .thenReturn(observable)
    }


    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
                .thenReturn(store)
    }


    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }


    private fun stubSaveProjects(completable: Completable) {
        whenever(store.saveProjects(any()))
                .thenReturn(completable)
    }

}