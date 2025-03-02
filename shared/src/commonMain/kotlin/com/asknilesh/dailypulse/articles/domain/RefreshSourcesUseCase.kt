package com.asknilesh.dailypulse.articles.domain

import com.asknilesh.dailypulse.articles.data.ArticleRepository
import com.asknilesh.dailypulse.articles.data.mapArticles
import com.asknilesh.dailypulse.articles.data.mapSources
import com.asknilesh.dailypulse.articles.data.mapToSourceRaw

class RefreshSourcesUseCase(private val repository: ArticleRepository) {

    suspend operator fun invoke(): List<Source> {
        val sources = repository.refreshSources()
        return mapSources(sources)
    }
}