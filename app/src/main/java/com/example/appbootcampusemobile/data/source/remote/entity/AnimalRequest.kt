package com.example.appbootcampusemobile.data.source.remote.entity

import com.google.gson.annotations.SerializedName

class AnimalRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("species")
    val species: String,
    @SerializedName("image")
    val image: String
)