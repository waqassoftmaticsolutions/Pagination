package com.plcoding.composepaging3caching.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.plcoding.composepaging3caching.Model.Movie

@Composable
fun BeerScreen(
    weathers: LazyPagingItems<Movie>
) {
//    val context = LocalContext.current
//    LaunchedEffect(key1 = weathers.loadState) {
//        if(weathers.loadState.refresh is LoadState.Error) {
//            Toast.makeText(
//                context,
//                "Error: " + (weathers.loadState.refresh as LoadState.Error).error.message,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }

    Box(modifier = Modifier.fillMaxSize()) {
        if(weathers.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(weathers) { weather ->
                    if(weather != null) {
                        BeerItem(
                            weather = weather,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                item {
                    if(weathers.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}