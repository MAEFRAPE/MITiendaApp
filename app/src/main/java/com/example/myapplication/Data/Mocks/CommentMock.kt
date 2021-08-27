package com.example.myapplication.Data.Mocks

import com.example.myapplication.Data.Models.Comment

class CommentMock {
    fun loadComments():List<Comment>{
        return listOf(
            Comment("1","https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Carlos Beltran","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Comment("2","https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Pepito Perez","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Comment("3","https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Manuel Franco","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Comment("4","https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Pedro Piedras","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Comment("5","https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Esteban Dido","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Comment("6","https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Elsa Patico","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            Comment("7","https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Elvis Cocho","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),

            )
    }
}