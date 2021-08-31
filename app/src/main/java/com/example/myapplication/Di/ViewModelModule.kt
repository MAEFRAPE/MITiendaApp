package com.example.myapplication.Di

import com.example.myapplication.UI.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel{HomeViewModels(get())}
    viewModel{CommentViewmodel(get())}
    viewModel{ProductViewModel(get())}
    viewModel{SplasViewModel(get(),get(),get() )}
    viewModel{LoginViewModel(get ())}
    viewModel{CarruselViewModel(get ())}
}