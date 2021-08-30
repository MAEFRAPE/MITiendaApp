package com.example.myapplication.Data.Repositorios

import com.example.myapplication.Data.Models.Producto
import com.example.myapplication.Utils.Constants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.limerse.slider.model.CarouselItem
import kotlinx.coroutines.tasks.await

class CarruselRepositiry(private val dataSourceFirebase: FirebaseFirestore) {
    private val db: CollectionReference = dataSourceFirebase.collection(Constants.CARRUSEL_COLECTION)

    suspend fun loadImagenes():List<CarouselItem>{

        val snapshot = db.get().await()
        return snapshot.toObjects(CarouselItem::class.java)
    }
}