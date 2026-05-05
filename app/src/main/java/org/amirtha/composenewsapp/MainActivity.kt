package org.amirtha.composenewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.amirtha.composenewsapp.data.api.NewsApiService
import org.amirtha.composenewsapp.data.repo.NewsRepository
import org.amirtha.composenewsapp.ui.screen.NewsListScreen
import org.amirtha.composenewsapp.ui.theme.ComposeNewsAppTheme
import org.amirtha.composenewsapp.viewmodel.NewsViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(NewsApiService::class.java)
    private val repository = NewsRepository(api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = NewsViewModel(repository)

        setContent {
            ComposeNewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsListScreen(
                        viewModel = viewModel,
                        apiKey = stringResource(R.string.API_KEY)
                    )
                    innerPadding
                }
            }
        }
    }
}