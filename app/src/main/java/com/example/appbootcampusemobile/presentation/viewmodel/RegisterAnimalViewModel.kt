package com.example.appbootcampusemobile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbootcampusemobile.data.repository.AnimalRepositoryImpl
import com.example.appbootcampusemobile.domain.model.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterAnimalViewModel : ViewModel() {

    private val repositoryImpl: AnimalRepositoryImpl = AnimalRepositoryImpl()

    private val _registerAnimalSuccess: MutableLiveData<List<Animal>> = MutableLiveData()
    val registerAnimalSuccess: LiveData<List<Animal>> = _registerAnimalSuccess

    private val _registerAnimalError: MutableLiveData<String> = MutableLiveData()
    val registerAnimalError: LiveData<String> = _registerAnimalError

    fun registerAnimal(name: String, image: String, description: String, species: String, age: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.registerAnimal(
                name,
                image,
                description,
                species,
                age
            ).catch { exception ->
                _registerAnimalError.postValue(exception.message)
            }.collect { animalResponse ->
                _registerAnimalSuccess.postValue(animalResponse)
            }
        }
    }
}