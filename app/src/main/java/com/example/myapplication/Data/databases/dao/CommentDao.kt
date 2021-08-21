package com.example.myapplication.Data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.Data.Models.Comment
@Dao
interface CommentDao {

    @Insert
    suspend fun insertComment(comment:List<Comment>)

    @Query("SELECT * FROM Comment")
    suspend fun getAllComments():List<Comment>
}