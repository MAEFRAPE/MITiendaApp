package com.example.myapplication.Data.Repositorios


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await


class UserRepository(private val dataSourceFirebase: FirebaseAuth) {
    suspend fun loginIn():FirebaseUser?{
        return dataSourceFirebase.currentUser

    }
}