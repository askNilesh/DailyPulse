package com.asknilesh.dailypulse.di

import com.asknilesh.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    networkModule,
    articlesModule
)