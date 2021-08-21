package com.example.myapplication.Data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Data.Models.Storeinfo

@Dao
interface StoreDao {

    @Insert
    suspend fun insertStore(store:Storeinfo)

    @Query("SELECT * FROM Storeinfo LIMIT 1")
    suspend fun selectStore():Storeinfo?

}