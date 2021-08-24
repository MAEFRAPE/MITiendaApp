package com.example.myapplication.Di

import com.example.myapplication.Data.Repositorios.CommentRepository
import com.example.myapplication.Data.Repositorios.HomeRepository
import com.example.myapplication.Data.Repositorios.ProductRepository
import com.example.myapplication.Data.Repositorios.UserRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        HomeRepository(get(),get())
    }
    single {
        CommentRepository(get (),get())
    }
    single {
        ProductRepository(get (),get())
    }
    single {
        UserRepository(get (),get(),get())
    }
}