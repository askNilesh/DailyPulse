package com.asknilesh.dailypulse.articles.domain

import com.asknilesh.dailypulse.articles.data.ArticleRepository
import com.asknilesh.dailypulse.articles.data.mapArticles

class RefreshArticlesUseCase(private val repository: ArticleRepository) {

    suspend fun getArticles(): List<Article> {
        val articlesRaw = repository.refreshArticles()
        return mapArticles(articlesRaw)
    }
}