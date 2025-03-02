package com.asknilesh.dailypulse.articles.data

import com.asknilesh.dailypulse.db.DailyPulseDatabase

class ArticleCacheDataSourceImpl(private val database: DailyPulseDatabase) :
    ArticleCacheDataSource {
    override suspend fun getArticles(): List<ArticleRaw> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    override suspend fun insertArticles(articles: List<ArticleRaw>) {
        database.transaction {
            articles.forEach { article ->
                database.dailyPulseDatabaseQueries.insertArticle(
                    title = article.title,
                    desc = article.desc,
                    date = article.date,
                    imageUrl = article.imageUrl
                )
            }
        }
    }

    override suspend fun removeAllArticles() {
        database.dailyPulseDatabaseQueries.removeAllArticles()
    }

    override suspend fun getSources(): List<SourceRaw> {
        return database.dailyPulseDatabaseQueries.selectAllSources(::mapToSourceRaw).executeAsList()
    }

    override suspend fun insertSources(sources: List<SourceRaw>) {
        database.transaction {
            sources.forEach { source ->
                database.dailyPulseDatabaseQueries.insertSource(
                    id = source.id,
                    name = source.name,
                    description = source.description,
                    country = source.country,
                    language = source.language
                )
            }
        }
    }

    override suspend fun removeAllSources() {
        database.dailyPulseDatabaseQueries.removeAllArticles()
    }


}