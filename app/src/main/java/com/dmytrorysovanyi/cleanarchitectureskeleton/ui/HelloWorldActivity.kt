@file:OptIn(ExperimentalMaterialApi::class)

package com.dmytrorysovanyi.cleanarchitectureskeleton.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dmytrorysovanyi.cleanarchitectureskeleton.theme.CleanArchitectureSkeletonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HelloWorldViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureSkeletonTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting(
                        isRefreshing = viewModel.state.isRefreshing,
                        items = viewModel.state.stringList,
                        onRefresh = { viewModel.refresh() },
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, isRefreshing: Boolean, items: List<String>, onRefresh: () -> Unit) {
    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = onRefresh)

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Simple text") },
                actions = {
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
            PullRefreshIndicator(refreshing = isRefreshing, state = pullRefreshState)
            LazyColumn(
                state = rememberLazyListState(),
                contentPadding = innerPadding,
            ) {
                items(items) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CleanArchitectureSkeletonTheme {
        Greeting(
            isRefreshing = false,
            items = emptyList(),
            onRefresh = {},
        )
    }
}
