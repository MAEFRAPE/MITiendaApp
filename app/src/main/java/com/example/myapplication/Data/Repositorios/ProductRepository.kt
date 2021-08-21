package com.example.myapplication.Data.Repositorios

import com.example.myapplication.Data.Models.Producto
import com.example.myapplication.Data.databases.dao.ProductDao
import com.example.myapplication.Utils.Constants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProductRepository(private val dataSourse:ProductDao,private val dataSourceFirebase: FirebaseFirestore) {
    private val db: CollectionReference = dataSourceFirebase.collection(Constants.PRODUCTS_COLECTION)

    suspend fun loadProducts():List<Producto>{
        /*return withContext(Dispatchers.IO){
            dataSourse.getAllProduct()
        }*/
        val snapshot = db.get().await()
        return snapshot.toObjects(Producto::class.java)
    }
    suspend fun insertProduct(product:List<Producto>){
        withContext(Dispatchers.IO){
            val temp = dataSourse.getAllProduct()
            if (temp.isEmpty()){
                dataSourse.insertProductos(product)
            }

        }
    }
}