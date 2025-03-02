package com.asknilesh.dailypulse.articles.di

import com.asknilesh.dailypulse.articles.data.ArticlesService
import com.asknilesh.dailypulse.articles.domain.GetArticlesUseCase
import com.asknilesh.dailypulse.articles.presentation.ArticlesViewModel
import com.asknilesh.dailypulse.articles.domain.RefreshArticlesUseCase
import com.asknilesh.dailypulse.articles.data.ArticleCacheDataSource
import com.asknilesh.dailypulse.articles.data.ArticleCacheDataSourceImpl
import com.asknilesh.dailypulse.articles.data.ArticleRepository
import com.asknilesh.dailypulse.articles.data.ArticleRepositoryImpl
import com.asknilesh.dailypulse.articles.domain.GetSourcesUseCase
import com.asknilesh.dailypulse.articles.domain.RefreshSourcesUseCase
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<GetArticlesUseCase> { GetArticlesUseCase((get())) }
    single<GetSourcesUseCase> { GetSourcesUseCase((get())) }
    single<RefreshSourcesUseCase> { RefreshSourcesUseCase((get())) }
    single<RefreshArticlesUseCase> { RefreshArticlesUseCase((get())) }
    single<ArticlesViewModel> { ArticlesViewModel(get(), get(), get(), get()) }
    single<ArticleCacheDataSource> { ArticleCacheDataSourceImpl(get()) }
    single<ArticleRepository> { ArticleRepositoryImpl(get(), get()) }
}