package ru.artempugachev.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.artempugachev.cache.db.ProjectDbInfo.COLUMN_IS_BOOKMARKED
import ru.artempugachev.cache.db.ProjectDbInfo.COLUMN_PROJECT_ID
import ru.artempugachev.cache.db.ProjectDbInfo.TABLE_NAME
import ru.artempugachev.cache.model.CachedProject


@Dao
abstract class CachedProjectsDao {

    @Query(GET_PROJECTS)
    abstract fun getProjects(): Flowable<List<CachedProject>>


    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()


    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>


    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean, projectId: String)


    companion object QUERIES {
        const val GET_PROJECTS = "SELECT * FROM $TABLE_NAME"
        const val DELETE_PROJECTS = "DELETE * FROM $TABLE_NAME"

        const val QUERY_BOOKMARKED_PROJECTS = "SELECT * FROM $TABLE_NAME " +
                "WHERE $COLUMN_IS_BOOKMARKED = 1"

        const val QUERY_UPDATE_BOOKMARK_STATUS = "UPDATE $TABLE_NAME " +
                "SET $COLUMN_IS_BOOKMARKED = :isBookmarked WHERE " +
                "$COLUMN_PROJECT_ID = :projectId"

    }
}

