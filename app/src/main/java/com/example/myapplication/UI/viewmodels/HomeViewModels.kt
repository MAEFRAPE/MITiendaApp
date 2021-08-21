package com.example.myapplication.UI.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.Models.Storeinfo
import com.example.myapplication.Data.Repositorios.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModels(private val repo:HomeRepository): ViewModel() {

    private var _info: MutableLiveData<Storeinfo> = MutableLiveData()
    val info: LiveData<Storeinfo> get() = _info

    fun loadStoreInfo(){

        viewModelScope.launch {
            val result = repo.loadStoreInfo()
            _info.postValue(result)
        }

    }
}