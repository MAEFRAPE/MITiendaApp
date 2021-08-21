package com.example.myapplication.Data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto (
    @PrimaryKey val id: Int?= null,
    val image: String?= null,
    val name: String?= null,
    val price: String?= null,
    val description: String?= null

        )