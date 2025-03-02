package com.asknilesh.dailypulse.articles.domain

import com.asknilesh.dailypulse.articles.data.ArticleRepository
import com.asknilesh.dailypulse.articles.data.mapArticles

class GetArticlesUseCase(private val repository: ArticleRepository) {

    suspend fun getArticles(): List<Article> {
        val articlesRaw = repository.getArticles()
        return mapArticles(articlesRaw)
    }

}