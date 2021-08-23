package com.example.myapplication.Data.Mocks

import com.example.myapplication.Data.Models.Storeinfo

class StoreInfoMocks {
    fun loadStoreInfo(): Storeinfo{
        return Storeinfo(
            1,
            "Mi tienda",
            "https://www.gadae.com/blog/wp-content/uploads/mi-tienda.jpg",
            "Calle falsa 123 Bogota",
            "2993456"
        )
    }
}