package com.asknilesh.dailypulse.articles.data

interface ArticleCacheDataSource
{
    suspend fun getArticles(): List<ArticleRaw>
    suspend fun insertArticles(articles: List<ArticleRaw>)
    suspend fun removeAllArticles()

    suspend fun getSources(): List<SourceRaw>
    suspend fun insertSources(sources: List<SourceRaw>)
    suspend fun removeAllSources()
}