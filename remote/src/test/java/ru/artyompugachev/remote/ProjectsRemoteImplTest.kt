package ru.artyompugachev.remote

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import ru.artempugachev.data.model.ProjectEntity
import ru.artempugachev.remote.ProjectsRemoteImpl
import ru.artempugachev.remote.mapper.ProjectsResponseModelMapper
import ru.artempugachev.remote.model.ProjectModel
import ru.artempugachev.remote.model.ProjectsResponseModel
import ru.artempugachev.remote.service.GithubTrendingService
import ru.artyompugachev.remote.test.factory.ProjectDataFactory

class ProjectsRemoteImplTest {
    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)


    @Test
    fun getProjectsCompletes() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
                ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
                ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }


    @Test
    fun getProjectsCallsServer() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
                ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
                ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }


    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }


    @Test
    fun getProjectsCallsServerWithCorrectParameters() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
                ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(),
                ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }


    private fun stubGithubTrendingServiceSearchRepositories(observable:
                                                            Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
                .thenReturn(observable)
    }


    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel,
                                                            entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
                .thenReturn(entity)
    }
}