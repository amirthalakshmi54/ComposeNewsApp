package org.amirtha.composenewsapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.amirtha.composenewsapp.ui.components.NewsItem
import org.amirtha.composenewsapp.viewmodel.NewsViewModel

@Composable
fun NewsListScreen(viewModel: NewsViewModel, apiKey: String) {

    val news by viewModel.news.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchNews(apiKey)
    }

    if (loading) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn {
            items(news) { article ->
                NewsItem(article = article, onClick = {})
            }
        }
    }
}