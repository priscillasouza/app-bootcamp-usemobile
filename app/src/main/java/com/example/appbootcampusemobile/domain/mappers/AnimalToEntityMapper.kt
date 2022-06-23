package com.example.appbootcampusemobile.domain.mappers

import com.example.appbootcampusemobile.data.source.local.entity.AnimalEntity
import com.example.appbootcampusemobile.domain.model.Animal

class AnimalToEntityMapper: IMapper<Animal, AnimalEntity > {
    override fun transform(entity: Animal): AnimalEntity {
        return AnimalEntity(
            id = entity.id,
            name = entity.name?: "",
            description = entity.description?: "",
            image = entity.image?: "",
            species = entity.species?: "",
            age = entity.age?: 0,
            favorite = entity.favorite
        )
    }
}