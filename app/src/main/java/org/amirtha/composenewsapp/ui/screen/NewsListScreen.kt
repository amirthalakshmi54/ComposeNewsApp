package org.amirtha.composenewsapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.amirtha.composenewsapp.data.model.Article
import org.amirtha.composenewsapp.ui.components.NewsItem
import org.amirtha.composenewsapp.viewmodel.NewsViewModel

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel,
    apiKey: String,
    onItemClick: (Article) -> Unit
) {
    val news by viewModel.news.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchNews(apiKey)
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp).align(Alignment.Center))
        }
    } else {
        LazyColumn {
            items(news) { article ->
                NewsItem(
                    article = article,
                    onClick = { onItemClick(article) }
                )
            }
        }
    }
}