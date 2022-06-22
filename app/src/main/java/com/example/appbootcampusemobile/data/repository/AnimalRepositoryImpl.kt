package com.example.appbootcampusemobile.data.repository

import com.example.appbootcampusemobile.data.source.remote.api.Api
import com.example.appbootcampusemobile.data.source.remote.entity.AnimalRequest
import com.example.appbootcampusemobile.domain.mappers.AnimalToModelMapper
import com.example.appbootcampusemobile.domain.model.Animal
import com.example.appbootcampusemobile.domain.repository.IAnimalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AnimalRepositoryImpl : IAnimalRepository {

    private var animalRoute = Api("https://bootcamp-ios-api.herokuapp.com/api/v1/").create()

    private var animalToModelMapper = AnimalToModelMapper()

    override suspend fun registerAnimal(
        name: String,
        image: String,
        description: String,
        species: String,
        age: Int
    ): Flow<List<Animal>> {
        return flow {
            animalRoute.registerAnimal(
                body = AnimalRequest(
                    name = name,
                    image = image,
                    description = description,
                    species = species,
                    age = age.toString().toInt()
                )
            ).let { response ->
                    if (response.isSuccessful && response.body() != null) {
                        animalToModelMapper.transform(response.body()!!)
                    } else {
                        throw HttpException(response)
                    }
                }.let {
                    emit(it)
            }
        }
    }

    override suspend fun getAnimals(): Flow<List<Animal>> {
        return flow {
            animalRoute.getAnimals().let { animalResponse ->
                if (animalResponse.isSuccessful && animalResponse.body() != null) {
                    animalToModelMapper.transform(animalResponse.body()!!.listAnimals)
                }else {
                    throw HttpException(animalResponse)
                }
            }.let {
                emit(it)
            }
        }
    }
}