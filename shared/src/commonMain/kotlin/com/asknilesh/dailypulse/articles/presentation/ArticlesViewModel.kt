package com.asknilesh.dailypulse.articles.presentation

import com.asknilesh.dailypulse.BaseViewModel
import com.asknilesh.dailypulse.articles.domain.RefreshArticlesUseCase
import com.asknilesh.dailypulse.articles.domain.GetArticlesUseCase
import com.asknilesh.dailypulse.articles.domain.GetSourcesUseCase
import com.asknilesh.dailypulse.articles.domain.RefreshSourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArticlesViewModel(
    private val useCase: GetArticlesUseCase,
    private val refreshArticlesUseCase: RefreshArticlesUseCase,
    private val getSourcesUseCase: GetSourcesUseCase,
    private val refreshSourcesUseCase: RefreshSourcesUseCase
) : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> = _articlesState.asStateFlow()


    init {
        getArticles()
    }


    private fun getArticles() {
        scope.launch {
            _articlesState.update { it.copy(loading = true) }
            val sources = getSourcesUseCase()
            val fetchedArticles = useCase.getArticles()
            _articlesState.emit(
                ArticlesState(
                    articles = fetchedArticles,
                    loading = false,
                    sources = sources
                )
            )
        }
    }

    fun refreshArticles() {
        _articlesState.update { it.copy(loading = true) }
        scope.launch {
            val fetchedArticles = refreshArticlesUseCase.getArticles()
            val sources = refreshSourcesUseCase().distinctBy { it.name }
            _articlesState.emit(
                ArticlesState(
                    articles = fetchedArticles,
                    loading = false,
                    sources = sources
                )
            )
        }
    }

}

