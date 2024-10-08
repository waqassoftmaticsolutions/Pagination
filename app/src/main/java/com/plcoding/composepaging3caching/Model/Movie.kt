package com.plcoding.composepaging3caching.Model

data class Movie(
//    val page: Int,
    val id: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val adult: Boolean,
    val backdropPath: String,
    //val genreIds: List<Int>,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)