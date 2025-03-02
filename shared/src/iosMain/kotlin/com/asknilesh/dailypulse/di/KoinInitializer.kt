package com.asknilesh.dailypulse.di

import com.asknilesh.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedKoinModules + databaseModule

    startKoin {
        modules(modules)
    }

}

class ArticleInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}

