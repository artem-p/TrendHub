package ru.artempugachev.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.artempugachev.cache.db.ProjectDbInfo.CONFIG_TABLE_NAME
import ru.artempugachev.cache.model.Config


@Dao
abstract class ConfigDao {

    @Query(QUERY_CONFIG)
    abstract fun getConfig(): Flowable<Config>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)


    companion object {
        const val QUERY_CONFIG = "SELECT * FROM $CONFIG_TABLE_NAME"
    }
}