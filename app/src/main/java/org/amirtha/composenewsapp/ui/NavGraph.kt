package org.amirtha.composenewsapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.google.gson.Gson
import org.amirtha.composenewsapp.data.model.Article
import org.amirtha.composenewsapp.ui.screen.NewsDetailScreen
import org.amirtha.composenewsapp.ui.screen.NewsListScreen
import org.amirtha.composenewsapp.viewmodel.NewsViewModel

@Composable
fun NavGraph(modifier: Modifier, viewModel: NewsViewModel, apiKey: String) {

    val navController = rememberNavController()

    NavHost(modifier = modifier, navController = navController, startDestination = "list") {

        composable("list") {
            NewsListScreen(
                viewModel = viewModel,
                apiKey = apiKey,
                onItemClick = { article ->
                    viewModel.selectArticle(article)
                    navController.navigate("detail")
                }
            )
        }

        composable("detail") {
            val article by viewModel.selectedArticle.collectAsState()

            article?.let {
                NewsDetailScreen(article = it)
            }
        }
    }
}