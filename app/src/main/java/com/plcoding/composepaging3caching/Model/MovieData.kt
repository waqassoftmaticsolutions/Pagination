package com.plcoding.composepaging3caching.Model

data class MovieData(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)