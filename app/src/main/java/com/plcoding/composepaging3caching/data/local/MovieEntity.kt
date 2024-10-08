package com.plcoding.composepaging3caching.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey val id: Int,
//    val page: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val adult: Boolean,
    val backdropPath: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)



data class MovieEntityDto(
    @PrimaryKey val id: Int,
    val price:Int,
//    val page: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val adult: Boolean,
    val backdropPath: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)