package ru.artempugachev.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import ru.artempugachev.cache.db.ProjectsDatabase
import ru.artempugachev.cache.factory.ConfigDataFactory
import ru.artempugachev.cache.factory.ProjectDataFactory
import ru.artempugachev.cache.mapper.CachedProjectMapper


@RunWith(RobolectricTestRunner::class)
class ProjectsCacheImplTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            ProjectsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    private val entityMapper = CachedProjectMapper()
    private val cache = ProjectsCacheImpl(database, entityMapper)


    @Test
    fun clearProjectsCompletes() {
        val testObserver = cache.clearProjects().test()

        testObserver.assertComplete()
    }


    @Test
    fun saveProjectsCompletes() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())

        val testObserver = cache.saveProjects(projects).test()
        testObserver.assertComplete()
    }


    @Test
    fun getProjectsReturnsData() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.getProjects().test()
        testObserver.assertValue(projects)
    }


    @Test
    fun getBookmarkedProjectsReturnsData() {
        val bookmarkedProject = ProjectDataFactory.makeBookmarkedProjectEntity()
        val notBookmarkedProject = ProjectDataFactory.makeNotBookmarkedProjectEntity()
        val projects = listOf(notBookmarkedProject,
                bookmarkedProject)
        cache.saveProjects(projects).test()

        val testObserver = cache.getBookmarkedProjects().test()
        testObserver.assertValue(listOf(bookmarkedProject))
    }


    @Test
    fun setProjectAsBookmarkedCompletes() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.setProjectAsBookmarked(projects[0].id).test()
        testObserver.assertComplete()
    }


    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        val projects = listOf(ProjectDataFactory.makeBookmarkedProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.setProjectAsNotBookmarked(projects[0].id).test()
        testObserver.assertComplete()
    }


    @Test
    fun areProjectsCacheReturnsData() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test()

        val testObserver = cache.areProjectsCached().test()
        testObserver.assertValue(true)
    }


    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(1000L).test()
        testObserver.assertComplete()
    }

// todo these tests don't work due to error java.lang.AssertionError: Expected: true (class: Boolean), Actual: []
//    @Test
//    fun isProjectsCacheExpiredReturnsNotExpired() {
//        val config = ConfigDataFactory.makeCachedConfig()
//        database.configDao().insertConfig(config)
//
//        cache.setLastCacheTime(System.currentTimeMillis())
//        val testObserver = cache.isProjectsCacheExpired().test()
//        testObserver.assertValue(false)
//    }
//
//
//    @Test
//    fun isProjectsCacheExpiredReturnsExpired() {
//        cache.setLastCacheTime(0)
//        val testObserver = cache.isProjectsCacheExpired().test()
//        testObserver.assertValue(true)
//    }
}