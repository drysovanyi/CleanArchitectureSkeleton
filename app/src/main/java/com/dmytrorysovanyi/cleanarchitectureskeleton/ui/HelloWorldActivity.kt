@file:OptIn(ExperimentalMaterial3Api::class)

package com.dmytrorysovanyi.cleanarchitectureskeleton.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
        Box(modifier = Modifier) {
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
