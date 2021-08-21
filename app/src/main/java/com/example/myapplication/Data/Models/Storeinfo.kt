package com.example.myapplication.Data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Storeinfo(
    @PrimaryKey val id:Int?= null,
    val name: String?= null,
    val image: String?= null,
    val adress: String?= null,
    val description: String?= null,
    val lat:Double? = null,
    val lng:Double? = null

    )
