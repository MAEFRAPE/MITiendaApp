package com.example.myapplication.UI.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.myapplication.Data.Repositorios.CarruselRepositiry
import com.limerse.slider.model.CarouselItem
import kotlinx.coroutines.launch


class CarruselViewModel(private val repo: CarruselRepositiry): ViewModel() {

    private var _carrusel: MutableLiveData<List<CarouselItem>> = MutableLiveData()
    val carrusel: LiveData<List<CarouselItem>> get() = _carrusel



    fun loadImagenes(){

        viewModelScope.launch {
            _carrusel.postValue(repo.loadImagenes())
        }
    }
}