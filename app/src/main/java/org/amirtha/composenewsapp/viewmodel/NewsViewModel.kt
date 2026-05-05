package org.amirtha.composenewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.amirtha.composenewsapp.data.model.Article
import org.amirtha.composenewsapp.data.repo.NewsRepository

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> = _news

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun fetchNews(apiKey: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = repository.getNews(apiKey)
                _news.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}