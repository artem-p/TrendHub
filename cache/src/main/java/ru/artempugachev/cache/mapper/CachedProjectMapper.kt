package ru.artempugachev.cache.mapper

import ru.artempugachev.cache.model.CachedProject
import ru.artempugachev.data.model.ProjectEntity
import javax.inject.Inject


class CachedProjectMapper @Inject constructor(): CacheMapper<CachedProject, ProjectEntity> {
    override fun mapFromCached(cached: CachedProject): ProjectEntity {
        return ProjectEntity(cached.id, cached.name, cached.fullName, cached.starCount,
                cached.dateCreated, cached.ownerName, cached.ownerAvatar,
                cached.isBookmarked)
    }


    override fun mapToCached(entity: ProjectEntity): CachedProject {
        return CachedProject(entity.id, entity.name, entity.fullName, entity.starCount,
                entity.dateCreated, entity.ownerName, entity.ownerAvatar,
                entity.isBookmarked)
    }
}