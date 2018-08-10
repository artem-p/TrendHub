package ru.artempugachev.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import ru.artempugachev.domain.interactor.bookmark.BookmarkProject
import ru.artempugachev.domain.interactor.bookmark.UnbookmarkProject
import ru.artempugachev.domain.interactor.browse.GetProjects
import ru.artempugachev.domain.model.Project
import ru.artempugachev.presentation.mapper.ProjectViewMapper
import ru.artempugachev.presentation.model.ProjectView
import ru.artempugachev.presentation.state.Resource
import ru.artempugachev.presentation.state.ResourceState
import javax.inject.Inject

class BrowseProjectsViewModel @Inject constructor(
        private val getProjects: GetProjects,
        private val bookmarkProject: BookmarkProject,
        private val unBookmarkProject: UnbookmarkProject,
        private val mapper: ProjectViewMapper
): ViewModel() {
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()


    override fun onCleared() {
        getProjects.dispose()
        super.onCleared()
    }


    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }


    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProjects.execute(ProjectsSubscriber())
    }


    fun bookmarkProject(projectId: String) {
        return bookmarkProject.execute(BookmarkProjectsSubscriber(),
                BookmarkProject.Params.forProject(projectId))
    }


    fun unbookmarkProject(projectId: String) {
        return unBookmarkProject.execute(BookmarkProjectsSubscriber(),
                UnbookmarkProject.Params.forProject(projectId))
    }


    inner class BookmarkProjectsSubscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data,
                    e.localizedMessage))
        }

    }



    inner class ProjectsSubscriber : DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }


        override fun onComplete() {
        }


        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.message))
        }
    }
}