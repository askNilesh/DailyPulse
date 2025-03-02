package com.asknilesh.dailypulse.articles.data

class ArticleRepositoryImpl(
    private val articleCacheDataSource: ArticleCacheDataSource,
    private val service: ArticlesService
) : ArticleRepository {


    override suspend fun getArticles(): List<ArticleRaw> {
        val articles = articleCacheDataSource.getArticles()
        if (articles.isEmpty()) {
            val response = service.fetchArticles()
            articleCacheDataSource.insertArticles(response)
            return response
        }
        return articles
    }

    override suspend fun refreshArticles(): List<ArticleRaw> {
        articleCacheDataSource.removeAllArticles()
        val response = service.fetchArticles()
        articleCacheDataSource.insertArticles(response)
        return response
    }


    override suspend fun insertArticles(articles: List<ArticleRaw>) {
        articleCacheDataSource.insertArticles(articles)
    }

    override suspend fun removeAllArticles() {
        articleCacheDataSource.removeAllArticles()
    }


    override suspend fun getSources(): List<SourceRaw> {
        val sources = articleCacheDataSource.getSources()
        if (sources.isEmpty()) {
            val response = service.fetchSources()
            articleCacheDataSource.insertSources(response)
            return response
        }
        return sources
    }

    override suspend fun refreshSources(): List<SourceRaw> {
        articleCacheDataSource.removeAllSources()
        val response = service.fetchSources()
        articleCacheDataSource.insertSources(response)
        return response
    }

    override suspend fun insertSources(sources: List<SourceRaw>) {
        articleCacheDataSource.insertSources(sources)

    }

    override suspend fun removeAllSources() {
        articleCacheDataSource.removeAllSources()
    }
}