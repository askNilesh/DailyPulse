package com.asknilesh.dailypulse.articles.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {

    private val apiKey = ""
    private val category = "sports"
    private val country = "us"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse = httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&apiKey=$apiKey&category=$category").body()
        return response.articles
    }

    suspend fun fetchSources(): List<SourceRaw> {
        val response: SourceResponse = httpClient.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey").body()
        return response.sources
    }
}