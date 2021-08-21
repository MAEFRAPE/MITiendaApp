package com.example.myapplication.UI.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.Data.Models.Producto
import com.example.myapplication.Data.Models.Storeinfo
import com.example.myapplication.Data.Repositorios.CommentRepository
import com.example.myapplication.Data.Repositorios.HomeRepository
import com.example.myapplication.Data.Repositorios.ProductRepository
import com.example.myapplication.Data.Repositorios.UserRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class SplasViewModel(private val repo:CommentRepository,private val repoProduct: ProductRepository,
                     private val repoStore:HomeRepository,private val repoUser:UserRepository):ViewModel() {

    private var _user:MutableLiveData<FirebaseUser?> = MutableLiveData()
    val user: LiveData<FirebaseUser?> get() = _user

    fun insert(comments:List<Comment>,productos:List<Producto>, store:Storeinfo)  {

         viewModelScope.launch {
             repo.insertComment(comments)
             repoProduct.insertProduct(productos)
             repoStore.insertStore(store)
         }
     }

    fun loginIn(){

        viewModelScope.launch {
            _user.postValue(repoUser.loginIn())
        }


    }
}