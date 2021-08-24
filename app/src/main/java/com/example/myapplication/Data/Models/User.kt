package com.example.myapplication.Data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User (
    @PrimaryKey val id: String?= null,
    val nombre: String?= null,
    val apellido: String?= null,
    val email: String?= null,
    val telefono: String?= null,
    val imag: String?= null

)
