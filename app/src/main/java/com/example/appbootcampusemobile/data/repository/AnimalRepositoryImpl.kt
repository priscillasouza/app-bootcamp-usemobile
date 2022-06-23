package com.example.appbootcampusemobile.data.repository

import android.content.Context
import com.example.appbootcampusemobile.data.source.local.AnimalDao
import com.example.appbootcampusemobile.data.source.local.AnimalDatabase
import com.example.appbootcampusemobile.data.source.remote.api.Api
import com.example.appbootcampusemobile.data.source.remote.entity.AnimalRequest
import com.example.appbootcampusemobile.domain.mappers.AnimalEntityToModelMapper
import com.example.appbootcampusemobile.domain.mappers.AnimalToEntityMapper
import com.example.appbootcampusemobile.domain.mappers.AnimalToModelMapper
import com.example.appbootcampusemobile.domain.model.Animal
import com.example.appbootcampusemobile.domain.repository.IAnimalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AnimalRepositoryImpl(
    private val context: Context? = null
) : IAnimalRepository {

    private var animalRoute = Api("https://bootcamp-ios-api.herokuapp.com/api/v1/").create()

    private var animalDao: AnimalDao? = context?.run {
        AnimalDatabase.getDatabase(this).animalDao()
    }

    private var animalToModelMapper = AnimalToModelMapper()
    private var animalToEntityMapper = AnimalToEntityMapper()
    private var animalEntityToModelMapper = AnimalEntityToModelMapper()

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

    //Utilizando o combine para fazer a junção do que vem da api com o itens que já foram favoritado
    override suspend fun getAnimals(): Flow<List<Animal>> {
        return flow {
            animalRoute.getAnimals().let { animalResponse ->
                if (animalResponse.isSuccessful && animalResponse.body() != null) {
                    animalToModelMapper.transform(animalResponse.body()!!.listAnimals)
                } else {
                    throw HttpException(animalResponse)
                }
            }.let {
                emit(it)
            }
        }.combine(getFavorites()) { listAnimalAPI, listAnimalDao ->
            listAnimalAPI.map { e ->
                listAnimalDao.forEach { a ->
                    if (e.id == a.id) {
                        e.favorite = true
                        return@forEach
                    }
                }
                e
            }
        }

    }

    override suspend fun addAnimal(animal: Animal): Flow<Animal> {
        return flow {
            animalToEntityMapper.transform(animal)
                .also {
                    animalDao?.addAnimal(it) ?: throw Exception("AnimalDAO is null")
                }
        }
    }

    override suspend fun deleteAnimal(animal: Animal): Flow<Animal> {
        return flow {
            animalToEntityMapper.transform(animal)
                .also {
                    animalDao?.deleteAnimal(it) ?: throw Exception("AnimalDAO is null")
                }
        }
    }

    override suspend fun getFavorites(): Flow<List<Animal>> {
        return flow {
            animalDao?.getFavorites().let { listAnimalEntity ->
                listAnimalEntity?.let {
                    animalEntityToModelMapper.transform(it)
                } ?: throw Exception("AnimalDAO is null")
            }.let {
                emit(it)
            }
        }
    }
}