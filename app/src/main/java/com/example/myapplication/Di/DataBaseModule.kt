package com.example.myapplication.Di



import com.example.myapplication.Data.databases.AppDatabase
import org.koin.dsl.module

val databaseModule= module {
    single {AppDatabase.getInstance(get())}
    single { get<AppDatabase>().commentDao()}
    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().storeDao() }


}