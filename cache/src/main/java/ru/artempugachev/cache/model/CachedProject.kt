package ru.artempugachev.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ru.artempugachev.cache.db.ProjectDbInfo

@Entity(tableName = ProjectDbInfo.TABLE_NAME)
data class CachedProject (
    @PrimaryKey
    @ColumnInfo(name = ProjectDbInfo.COLUMN_PROJECT_ID)
    var id: String,

    @ColumnInfo(name = ProjectDbInfo.COLUMN_NAME)
    var name: String,

    @ColumnInfo(name = ProjectDbInfo.COLUMN_FULL_NAME)
    var fullName: String,

    @ColumnInfo(name = ProjectDbInfo.COLUMN_STAR_COUNT)
    var starCount: String,

    @ColumnInfo(name = ProjectDbInfo.COLUMN_DATE_CREATED)
    var dateCreated: String,

    @ColumnInfo(name = ProjectDbInfo.COLUMN_OWNER_NAME)
    var ownerName: String,

    @ColumnInfo(name = ProjectDbInfo.COLUMN_OWNER_AVATAR)
    var ownerAvatar: String,

    @ColumnInfo(name = ProjectDbInfo.COLUMN_IS_BOOKMARKED)
    var isBookmarked: Boolean
)