package com.example.appbootcampusemobile.presentation.viewmodel

import android.content.Context
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

class HomeViewModel(
    private val context: Context
) : ViewModel() {

    private var animalRepository = AnimalRepositoryImpl(context)

    private var _animalResponse: MutableLiveData<List<Animal>> = MutableLiveData()
    val animalResponse: LiveData<List<Animal>> = _animalResponse

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getAnimals() {
        viewModelScope.launch(Dispatchers.IO) {
            animalRepository.getAnimals().catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _animalResponse.postValue(it)
            }
        }
    }

    fun updateFavorites(animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            if(animal.favorite) {
                animalRepository.addAnimal(animal).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _animalResponse.postValue(arrayListOf(it))
                }
            }else {
                animalRepository.deleteAnimal(animal).catch { exception ->
                    _error.postValue(exception.message)
                }.collect {
                    _animalResponse.postValue(arrayListOf(it))
                }
            }
        }
    }
}