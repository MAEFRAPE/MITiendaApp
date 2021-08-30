package com.example.myapplication.Di

import com.example.myapplication.Data.Repositorios.*
import org.koin.dsl.module

val repoModule = module {
    single {
        HomeRepository(get(),get())
    }
    single {
        CommentRepository(get())
    }
    single {
        ProductRepository(get (),get())
    }
    single {
        UserRepository(get (),get(),get())
    }
    single {
        CarruselRepositiry(get ())
    }
}