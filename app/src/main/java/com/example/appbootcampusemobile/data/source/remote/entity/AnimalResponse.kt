package com.example.appbootcampusemobile.data.source.remote.entity

import com.google.gson.annotations.SerializedName

data class AnimalResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("age")
    val age: Int? = 0,
    @SerializedName("species")
    val species: String? = "",
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String
)

data class ItemsAnimalResponse(
    @SerializedName("items")
    val listAnimals: List<AnimalResponse>
)