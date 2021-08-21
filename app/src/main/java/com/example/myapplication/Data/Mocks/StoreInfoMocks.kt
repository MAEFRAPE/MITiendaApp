package com.example.myapplication.Data.Mocks

import com.example.myapplication.Data.Models.Storeinfo

class StoreInfoMocks {
    fun loadStoreInfo(): Storeinfo{
        return Storeinfo(
            1,
            "Mi tienda",
            "https://www.gadae.com/blog/wp-content/uploads/mi-tienda.jpg",
            "Calle falsa 123 Bogota",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
        )
    }
}