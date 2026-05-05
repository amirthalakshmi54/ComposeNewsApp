package org.amirtha.composenewsapp.data.repo

import org.amirtha.composenewsapp.data.api.NewsApiService


class NewsRepository(private val api: NewsApiService) {

    suspend fun getNews(apiKey: String) = api.getTopHeadlines(apiKey = apiKey)
}