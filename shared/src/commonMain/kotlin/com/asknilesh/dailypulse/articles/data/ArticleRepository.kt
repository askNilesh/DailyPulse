package com.asknilesh.dailypulse.articles.data
interface ArticleRepository {
    suspend fun getArticles(): List<ArticleRaw>
    suspend fun refreshArticles(): List<ArticleRaw>
    suspend fun insertArticles(articles: List<ArticleRaw>)
    suspend fun removeAllArticles()

    suspend fun getSources(): List<SourceRaw>
    suspend fun refreshSources(): List<SourceRaw>
    suspend fun insertSources(articles: List<SourceRaw>)
    suspend fun removeAllSources()
}