package com.example.myapplication.UI.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.Models.Producto
import com.example.myapplication.Data.Repositorios.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel (private val repo:ProductRepository): ViewModel(){
    private var _product: MutableLiveData<List<Producto>> = MutableLiveData()
    val product: LiveData<List<Producto>> get() = _product

    private var _selected:MutableLiveData<Producto> = MutableLiveData()
    val selected: LiveData<Producto> get() = _selected

    fun loadProduct(){

        viewModelScope.launch {
            _product.postValue(repo.loadProducts())
        }
    }

    fun loadProductSelect(product:Producto){
        _selected.postValue(product)

        }

}