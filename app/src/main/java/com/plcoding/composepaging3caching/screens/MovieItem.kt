package com.plcoding.composepaging3caching.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.composepaging3caching.Model.Movie

@Composable
fun BeerItem(
    weather: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
                Column {
                    Text(text = "Title: ${weather.title}")
    //                Text("Vote Count: ${weather.voteCount}")
                    Text(weather.overview)
                }
            }
        }
    }
