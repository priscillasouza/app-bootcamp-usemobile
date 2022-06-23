package com.example.appbootcampusemobile.domain.mappers

import com.example.appbootcampusemobile.data.source.local.entity.AnimalEntity
import com.example.appbootcampusemobile.domain.model.Animal

//tranformando os dados da camada de dados para a camada da view
class AnimalEntityToModelMapper: IMapper<AnimalEntity, Animal> {
    override fun transform(entity: AnimalEntity ): Animal {
        return Animal(
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