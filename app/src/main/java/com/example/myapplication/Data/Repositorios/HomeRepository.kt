package com.example.myapplication.Data.Repositorios

import com.example.myapplication.Data.Models.Storeinfo
import com.example.myapplication.Data.databases.dao.StoreDao
import com.example.myapplication.Utils.Constants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class HomeRepository (private val dataSource:StoreDao,private val dataSourceFirebase: FirebaseFirestore){
    private val db: CollectionReference = dataSourceFirebase.collection(Constants.STORE_COLECTION)

    suspend fun loadStoreInfo():Storeinfo{
       /*return withContext(Dispatchers.IO){
           dataSource.selectStore()!!
       }*/
        val snapshot = db.get().await()
        return snapshot.toObjects(Storeinfo::class.java)[0]
    }
   /* suspend fun insert():Storeinfo{
        return withContext(Dispatchers.IO){
            dataSource.selectStore()!!
        }
    }*/
    suspend fun insertStore(store:Storeinfo){

        withContext(Dispatchers.IO){
            val temp = dataSource.selectStore()
            if (temp== null){
                dataSource.insertStore(store)

            }

        }
    }
}