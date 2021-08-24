package com.example.myapplication.Di
import com.example.myapplication.Data.Mocks.CommentMock
import com.example.myapplication.Data.Mocks.ProductMockus
import com.example.myapplication.Data.Mocks.StoreInfoMocks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.dsl.module
val dataSourceModule = module {
    single {
        StoreInfoMocks()
    }
    single {
        CommentMock()
    }
    single {
        ProductMockus()
    }
    single {
        FirebaseFirestore.getInstance()
    }
    single {
        FirebaseAuth.getInstance()
    }
    single {
        FirebaseStorage.getInstance().getReference()
    }
}
