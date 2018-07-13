package ru.artempugachev.data.store

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.artempugachev.data.model.ProjectEntity
import ru.artempugachev.data.repository.ProjectsCache
import ru.artempugachev.data.test.factory.DataFactory
import ru.artempugachev.data.test.factory.ProjectFactory


@RunWith(JUnit4::class)
class ProjectsCacheDataStoreTest {
    private val cache = mock<ProjectsCache>()
    private val store = ProjectsCacheDataStore(cache)


    @Test
    fun getProjectsCompletes() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()

        testObserver.assertComplete()
    }


    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetProjects(Observable.just(data))

        val testObserver = store.getProjects().test()

        testObserver.assertValue(data)
    }


    @Test
    fun getProjectsCallsCacheSource() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects()

        verify(cache).getProjects()
    }


    @Test
    fun saveProjectsCompletes() {
        stubSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())

        val testObserver = store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()

        testObserver.assertComplete()
    }


    @Test
    fun saveProjectsCallsCache() {
        stubSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())

        store.saveProjects(listOf(ProjectFactory.makeProjectEntity()))

        verify(cache).saveProjects(any())
    }


    @Test
    fun clearProjectsCompletes() {
        stubProjectsClearProjects(Completable.complete())

        val testObserver = store.clearProjects().test()
        testObserver.assertComplete()
    }


    @Test
    fun clearProjectsCallsCache() {
        stubProjectsClearProjects(Completable.complete())

        store.clearProjects()
        verify(cache).clearProjects()
    }


    @Test
    fun getBookmarkedProjectsCompletes() {
        stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }


    @Test
    fun getBookmarkedProjectsCallsCache() {
        stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        store.getBookmarkedProjects()
        verify(cache).getBookmarkedProjects()
    }


    @Test
    fun getBookmarkedProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubGetBookmarkedProjects(Observable.just(data))

        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertValue(data)
    }


    @Test
    fun setProjectsAsBookmarkedCompletes() {
        stubSetProjectAsBookmarked(Completable.complete())

        val testObserver = store.setProjectAsBookmarked(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }


    @Test
    fun setProjectsAsBookmarkedCallsCacheStore() {
        val projectId = DataFactory.randomString()

        stubSetProjectAsBookmarked(Completable.complete())
        store.setProjectAsBookmarked(projectId)

        verify(cache).setProjectAsBookmarked(projectId)
    }


    @Test
    fun setProjectsAsNotBookmarkedCompletes() {
        stubSetProjectAsNotBookmarked(Completable.complete())

        val testObserver = store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }


    @Test
    fun setProjectsAsNotBookmarkedCallsCacheStore() {
        val projectId = DataFactory.randomString()

        stubSetProjectAsNotBookmarked(Completable.complete())
        store.setProjectAsNotBookmarked(projectId)

        verify(cache).setProjectAsNotBookmarked(projectId)
    }

    /**
     * Stub methods
     * */

    private fun stubProjectsCacheGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getProjects())
                .thenReturn(observable)
    }


    private fun stubSaveProjects(completable: Completable) {
        whenever(cache.saveProjects(any()))
                .thenReturn(completable)
    }


    private fun stubProjectsCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
                .thenReturn(completable)
    }


    private fun stubProjectsClearProjects(completable: Completable) {
        whenever(cache.clearProjects())
                .thenReturn(completable)
    }


    private fun stubGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getBookmarkedProjects())
                .thenReturn(observable)
    }


    private fun stubSetProjectAsBookmarked(completable: Completable) {
        whenever(cache.setProjectAsBookmarked(any()))
                .thenReturn(completable)
    }


    private fun stubSetProjectAsNotBookmarked(completable: Completable) {
        whenever(cache.setProjectAsNotBookmarked(any()))
                .thenReturn(completable)
    }

}