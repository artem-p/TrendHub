package ru.artempugachev.remote.mapper

import ru.artempugachev.data.model.ProjectEntity
import ru.artempugachev.remote.model.ProjectModel

open class ProjectsResponseModelMapper: ModelMapper<ProjectModel, ProjectEntity> {
    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName,
                model.starCount.toString(), model.dateCreated, model.owner.ownerName,
                model.owner.ownerAvatar)
    }
}