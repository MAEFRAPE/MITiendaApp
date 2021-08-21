package com.example.myapplication.Di

import com.example.myapplication.UI.viewmodels.CommentViewmodel
import com.example.myapplication.UI.viewmodels.HomeViewModels
import com.example.myapplication.UI.viewmodels.ProductViewModel
import com.example.myapplication.UI.viewmodels.SplasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel{HomeViewModels(get())}
    viewModel{CommentViewmodel(get())}
    viewModel{ProductViewModel(get())}
    viewModel{SplasViewModel(get(),get(),get(),get() )}
}