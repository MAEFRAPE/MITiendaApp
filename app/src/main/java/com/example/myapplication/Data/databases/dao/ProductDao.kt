package com.example.myapplication.Data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Data.Models.Producto

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProductos(product:List<Producto>)

    @Query("SELECT * FROM Producto")
    suspend fun getAllProduct():List<Producto>
}