package ru.artempugachev.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ru.artempugachev.cache.ProjectDbContract

@Entity(tableName = ProjectDbContract.TABLE_NAME)
data class CachedProject (
    @PrimaryKey
    var id: String,
    var name: String,
    var fullName: String,
    var starCount: String,
    var dateCreated: String,
    var ownerName: String,
    var ownerAvatar: String,
    var isBookmarked: Boolean
)