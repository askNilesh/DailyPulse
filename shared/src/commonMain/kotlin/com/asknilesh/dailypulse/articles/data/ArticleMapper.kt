package com.asknilesh.dailypulse.articles.data

import com.asknilesh.dailypulse.articles.domain.Article
import com.asknilesh.dailypulse.articles.domain.Source
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> = articlesRaw.map { raw ->
    Article(
        title = raw.title,
        desc = raw.desc ?: "Click to find out more",
        date = getDaysAgoString(raw.date),
        imageUrl = raw.imageUrl
            ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
    )
}


fun mapSources(sources: List<SourceRaw>): List<Source> = sources.map { raw ->
    Source(
        id= raw.id,
                name= raw.name,
                description= raw.description,
        country= raw.country,
        language= raw.language,
        )
}

fun getDaysAgoString(date: String): String {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val days = today.daysUntil(
        Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
    )

    val result = when {
        abs(days) > 1 -> "${abs(days)} days ago"
        abs(days) == 1 -> "Yesterday"
        else -> "Today"
    }
    return result
}

fun mapToArticleRaw(
    title: String,
    desc: String?,
    date: String,
    url: String?
) = ArticleRaw(
    title = title,
    desc = desc,
    date = date,
    imageUrl = url
)

fun mapToSourceRaw(
    id: String,
    name: String,
    description: String,
    language: String,
    country: String
) = SourceRaw(
    id = id,
    name = name,
    description = description,
    language = language,
    country = country,
)