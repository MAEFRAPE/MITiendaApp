package com.example.myapplication.Data.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment (
    @PrimaryKey val id: String?= null,
    val image: String?= null,
    val name:String?= null,
    val message:String?= null
        )