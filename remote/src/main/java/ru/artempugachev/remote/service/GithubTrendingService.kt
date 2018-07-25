package ru.artempugachev.remote.service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.artempugachev.remote.model.ProjectsResponseModel


interface GithubTrendingService {
    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String)
            : Observable<ProjectsResponseModel>
}