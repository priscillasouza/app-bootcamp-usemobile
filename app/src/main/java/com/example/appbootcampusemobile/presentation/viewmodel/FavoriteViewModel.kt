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

class FavoriteViewModel(
    private val context: Context
) : ViewModel() {

    private var animalRepository = AnimalRepositoryImpl(context)

    private var _animalFavorite: MutableLiveData<List<Animal>> = MutableLiveData()
    val animalFavorite: LiveData<List<Animal>> = _animalFavorite

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            animalRepository.getFavorites().catch { exception ->
                _error.postValue(exception.message)
            }.collect {
                _animalFavorite.postValue(it)
            }
        }
    }
}