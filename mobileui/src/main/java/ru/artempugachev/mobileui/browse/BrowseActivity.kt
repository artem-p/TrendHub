package ru.artempugachev.mobileui.browse

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_browse.*
import ru.artempugachev.mobileui.R

class BrowseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)

        setUpBrowseRecycler()
    }


    private fun setUpBrowseRecycler() {
        recycler_projects.layoutManager = LinearLayoutManager(this)
    }
}
