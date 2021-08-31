package com.example.myapplication.UI.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.Models.User
import com.example.myapplication.Data.Repositorios.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Error

class LoginViewModel(private val repo:UserRepository):ViewModel() {

    private var _user: MutableLiveData<FirebaseUser> = MutableLiveData()
    private var _userDB:MutableLiveData<User> = MutableLiveData()

    val user: LiveData<FirebaseUser?> get() = _user
    val userDB: LiveData<User> get() = _userDB

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    fun registro(nombres:String, apellidos:String,email:String, tel:String, pass:String){

        viewModelScope.launch {
            try {
                _user.postValue(repo.registro(nombres, apellidos,email, tel, pass))
            }catch (e:Error){
                _error.postValue(e.message!!)
            }
        }
    }

     fun login(email: String,pass: String){
        viewModelScope.launch {
            try {
                _user.postValue(repo.login(email, pass))
            }catch (e:Error){
                _error.postValue(e.message!!)
            }
        }
    }

    fun ValidarCorreo(email: String){
        viewModelScope.launch {
            try {
                _user.postValue(repo.ValidarCorreo(email))
            }catch (e:Error){
                _error.postValue(e.message!!)
            }
        }

    }

    fun logout(){
        viewModelScope.launch {
            _user.postValue(repo.logout())
        }
    }
    fun loginIn(){

        viewModelScope.launch {
            _user.postValue(repo.loginIn())
        }

    }

    fun uploadImage(bitmap:Bitmap){
        viewModelScope.launch {
            _user.postValue(repo.uploadImage(bitmap))
        }
    }

     fun loadUser(idUser:String) {
         viewModelScope.launch {
             _userDB.postValue(repo.loadUser(idUser))
         }
     }
}