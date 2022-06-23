package com.example.appbootcampusemobile.domain.mappers

import com.example.appbootcampusemobile.data.source.remote.entity.AnimalResponse
import com.example.appbootcampusemobile.domain.model.Animal

class AnimalToModelMapper: IMapper<AnimalResponse, Animal> {
    override fun transform(entity: AnimalResponse): Animal {
        return Animal(
            id = entity.id ?: "",
            name = entity.name,
            description = entity.description,
            image = entity.image,
            species = entity.species,
            age = entity.age,
            favorite = false
        )
    }
}