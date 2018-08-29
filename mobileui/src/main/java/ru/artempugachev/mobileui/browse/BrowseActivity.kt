package ru.artempugachev.mobileui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import ru.artempugachev.domain.model.Project
import ru.artempugachev.mobileui.R
import ru.artempugachev.mobileui.bookmarked.BookmarkedActivity
import ru.artempugachev.mobileui.di.ViewModelFactory
import ru.artempugachev.mobileui.mapper.ProjectViewMapper
import ru.artempugachev.presentation.BrowseProjectsViewModel
import ru.artempugachev.presentation.model.ProjectView
import ru.artempugachev.presentation.state.Resource
import ru.artempugachev.presentation.state.ResourceState
import javax.inject.Inject

class BrowseActivity : AppCompatActivity() {

    @Inject lateinit var browseAdapter: BrowseAdapter
    @Inject lateinit var mapper: ProjectViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var browseViewModel: BrowseProjectsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BrowseProjectsViewModel::class.java)

        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this,
                Observer<Resource<List<ProjectView>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        browseViewModel.fetchProjects()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_bookmarked -> {
                startActivity(BookmarkedActivity.getStartIntent(this))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBrowseRecycler() {
        browseAdapter.projectListener = projectListener
        recycler_projects.layoutManager = LinearLayoutManager(this)
        recycler_projects.adapter = browseAdapter
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data?.map {
                    mapper.mapToView(it)
                })
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<Project>?) {
//        progress.visibility = View.GONE
//        projects?.let {
//            browseAdapter.projects = it
//            browseAdapter.notifyDataSetChanged()
//            recycler_projects.visibility = View.VISIBLE
//        } ?: run {
//
//        }
    }

    private val projectListener = object : ProjectListener {
        override fun onBookmarkedProjectClicked(projectId: String) {
            browseViewModel.unbookmarkProject(projectId)
        }

        override fun onProjectClicked(projectId: String) {
            browseViewModel.bookmarkProject(projectId)
        }
    }}
