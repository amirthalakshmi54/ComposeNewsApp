package org.amirtha.composenewsapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.amirtha.composenewsapp.data.model.Article

@Composable
fun NewsDetailScreen(article: Article) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = article.title ?: "", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.content ?: "")
    }
}