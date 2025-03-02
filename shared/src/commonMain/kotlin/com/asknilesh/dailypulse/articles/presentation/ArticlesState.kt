package com.asknilesh.dailypulse.articles.presentation

import com.asknilesh.dailypulse.articles.domain.Article
import com.asknilesh.dailypulse.articles.domain.Source

data class ArticlesState (
    val articles: List<Article> = listOf(),
    val sources: List<Source> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)