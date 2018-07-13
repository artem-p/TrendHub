package ru.artempugachev.data.store

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.artempugachev.data.model.ProjectEntity
import ru.artempugachev.data.repository.ProjectsRemote
import ru.artempugachev.data.test.factory.DataFactory
import ru.artempugachev.data.test.factory.ProjectFactory


@RunWith(JUnit4::class)
class ProjectsRemoteDataStoreTest {

    private val remote = mock<ProjectsRemote>()
    private val store = ProjectsRemoteDataStore(remote)


    @Test
    fun getProjectsCompletes() {
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        val testObserver = store.getProjects().test()

        testObserver.assertComplete()
    }


    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubGetProjects(Observable.just(data))
        val testObserver = store.getProjects().test()

        testObserver.assertValue(data)
    }


    @Test
    fun getProjectsCallsRemote() {
        stubGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))

        store.getProjects()

        verify(remote).getProjects()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowsException() {
        store.saveProjects(listOf()).test()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException() {
        store.clearProjects().test()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowsException() {
        store.getBookmarkedProjects().test()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun setProjectsAsBookmarkedThrowsException() {
        store.setProjectAsBookmarked(DataFactory.randomString()).test()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun setProjectsAsNotBookmarkedThrowsException() {
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
    }


    private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(remote.getProjects())
                .thenReturn(observable)
    }
}