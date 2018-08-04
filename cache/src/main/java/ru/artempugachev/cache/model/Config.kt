package ru.artempugachev.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ru.artempugachev.cache.db.ProjectDbInfo


@Entity(tableName = ProjectDbInfo.CONFIG_TABLE_NAME)
data class Config(
        @PrimaryKey(autoGenerate = true)
        var id: Int = -1,
        var lastCacheTime: Long)