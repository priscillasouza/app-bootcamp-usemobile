package com.example.appbootcampusemobile.domain.repository

import com.example.appbootcampusemobile.domain.model.Animal
import kotlinx.coroutines.flow.Flow

interface IAnimalRepository {
    suspend fun registerAnimal(
        name: String,
        image: String,
        description: String,
        species: String,
        age: Int
    ): Flow<List<Animal>>

    suspend fun getAnimals(): Flow<List<Animal>>

    suspend fun deleteAnimal(animal: Animal): Flow<Animal>

    suspend fun addAnimal(animal: Animal): Flow<Animal>

    suspend fun getFavorites(): Flow<List<Animal>>
}