package com.example.appbootcampusemobile.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal_table")
data class AnimalEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String? = "",
    val image: String? = "",
    val description: String? = "",
    val species: String? = "",
    val age: Int? = 0,
    val favorite: Boolean = false
)