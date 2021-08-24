package com.example.myapplication.Data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.Data.Models.Producto
import com.example.myapplication.Data.Models.Storeinfo
import com.example.myapplication.Data.databases.dao.CommentDao
import com.example.myapplication.Data.databases.dao.ProductDao
import com.example.myapplication.Data.databases.dao.StoreDao

@Database(entities = [Comment::class,Producto::class,Storeinfo::class ],version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun commentDao(): CommentDao
    abstract fun productDao():ProductDao
    abstract fun storeDao(): StoreDao

    companion object{
        @Volatile
        private var instance: AppDatabase? =null

        fun getInstance(context: Context):AppDatabase{
            if(instance== null){
                synchronized(this){

                    instance= Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"ejemplo.db")
                        .build()
                }

            }
            return instance!!
        }

    }
}