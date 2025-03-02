package com.asknilesh.dailypulse.android.di

import com.asknilesh.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        ArticlesViewModel(get(), get(),get(), get())
    }
}