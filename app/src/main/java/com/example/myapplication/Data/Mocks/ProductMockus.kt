package com.example.myapplication.Data.Mocks

import com.example.myapplication.Data.Models.Producto

class ProductMockus {
    fun loadProducts():List<Producto>{
        return listOf(
            Producto(1,"https://comfandivirtual.com.co/supermercados/70485-large_default/arroz-kilo.jpg","Arroz Roa","$2500","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Producto(2,"https://comfandivirtual.com.co/supermercados/1021-large_default/aceite-liquido-olio-soya-vegetal-frasco-2000ml.jpg","Aceite OleoSoya","$3500","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Producto(3,"https://metrocolombiafood.vteximg.com.br/arquivos/ids/159831-1000-1000/7702007043402-1.jpg?v=636670251334730000","Chocolate","$4000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Producto(4,"https://grupodiana.co/wp-content/uploads/2019/12/btn_grano_2.png","Frijol","$6000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Producto(5,"https://jumbocolombiafood.vteximg.com.br/arquivos/ids/3510553-1000-1000/7702511002933.jpg?v=637273105687100000","lenteja","$1000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
        )

    }
}