package ru.artempugachev.mobileui.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_project.view.*
import ru.artempugachev.mobileui.R
import ru.artempugachev.presentation.model.ProjectView
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    /**
     * we reuse ProjectView model from presentation layer here
     * in general, we can create own project model for this layer
     * and mapper to map from ProjectView
     * */
    var projects: List<ProjectView> = arrayListOf()
    var projectListener: ProjectListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_project, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return projects.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]

        holder.ownerName.text = project.ownerName
        holder.projectName.text = project.fullName

        Glide.with(holder.itemView.context)
                .load(project.ownerAvatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatarImage)

        val starResource = if (project.isBookmarked) {
            R.drawable.ic_star_black_24dp
        } else {
            R.drawable.ic_star_border_black_24dp
        }
        holder.bookmarkedImage.setImageResource(starResource)

        holder.itemView.setOnClickListener {
            if (project.isBookmarked) {
                projectListener?.onBookmarkedProjectClicked(project.id)
            } else {
                projectListener?.onProjectClicked(project.id)
            }
        }
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val avatarImage: ImageView = view.image_owner_avatar
        val ownerName: TextView = view.text_owner_name
        val projectName: TextView = view.text_project_name
        val bookmarkedImage: ImageView = view.image_bookmarked
    }
}