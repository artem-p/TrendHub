package ru.artempugachev.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.artempugachev.cache.db.ProjectsDatabase
import ru.artempugachev.cache.mapper.CachedProjectMapper
import ru.artempugachev.data.model.ProjectEntity
import ru.artempugachev.data.repository.ProjectsCache
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
        private val projectsDatabase: ProjectsDatabase,
        private val mapper: CachedProjectMapper)
    :ProjectsCache {


    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }


    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertProjects(
                    projects.map { mapper.mapToCached(it) }
            )

            Completable.complete()
        }
    }


    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects()
                .toObservable()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }


    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
                .toObservable()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }


    override fun setProjectAsBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun areProjectsCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun setLastCacheTime(lastCache: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun isProjectsCacheExpired(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}