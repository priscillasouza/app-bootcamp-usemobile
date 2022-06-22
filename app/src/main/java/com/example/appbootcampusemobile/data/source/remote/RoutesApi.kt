package com.example.appbootcampusemobile.data.source.remote

import com.example.appbootcampusemobile.data.source.remote.entity.AnimalRequest
import com.example.appbootcampusemobile.data.source.remote.entity.AnimalResponse
import com.example.appbootcampusemobile.data.source.remote.entity.ItemsAnimalResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RoutesApi {

    @POST("animals")
    suspend fun registerAnimal(
        @Body body: AnimalRequest
    ): Response<List<AnimalResponse>>

    @GET("animals")
    suspend fun getAnimals(): Response<ItemsAnimalResponse>
}