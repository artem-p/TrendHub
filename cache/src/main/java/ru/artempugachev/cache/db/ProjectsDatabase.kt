package ru.artempugachev.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ru.artempugachev.cache.dao.CachedProjectsDao
import ru.artempugachev.cache.dao.ConfigDao
import ru.artempugachev.cache.model.CachedProject
import ru.artempugachev.cache.model.Config
import javax.inject.Inject


@Database(entities = [(CachedProject::class), (Config::class)], version = 1)
abstract class ProjectsDatabase @Inject constructor(): RoomDatabase() {
    private var INSTANCE: ProjectsDatabase? = null
    private val lock = Any()


    abstract fun cachedProjectsDao(): CachedProjectsDao


    abstract fun configDao(): ConfigDao


    fun getInstance(context: Context): ProjectsDatabase {
        if (INSTANCE == null) {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            ProjectsDatabase::class.java, "projects.db")
                            .build()
                }
                return INSTANCE as ProjectsDatabase
            }
        }
        return INSTANCE as ProjectsDatabase
    }
}