package com.example.appbootcampusemobile.data.source.local

import androidx.room.*
import com.example.appbootcampusemobile.data.source.local.entity.AnimalEntity

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAnimal(animal: AnimalEntity): Long

    @Delete
    suspend fun deleteAnimal(animal: AnimalEntity)

    @Query("SELECT * FROM animal_table WHERE favorite = 1")
    suspend fun getFavorites(): List<AnimalEntity>
}