package com.plcoding.composepaging3caching.data.mappers

import com.plcoding.composepaging3caching.data.local.MovieEntity
import com.plcoding.composepaging3caching.data.remote.MovieResponseDto
import com.plcoding.composepaging3caching.Model.Movie


fun MovieEntity.toMovie(): Movie {
    return Movie(
//        page = page,
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath.orEmpty(),
        originalLanguage = this.originalLanguage.orEmpty(),
        originalTitle = this.originalTitle.orEmpty(),
        overview = this.overview.orEmpty(),
        popularity = this.popularity,
        posterPath = this.posterPath.orEmpty(),
        releaseDate = this.releaseDate.orEmpty(),
        title = this.title.orEmpty(),
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}


fun MovieResponseDto.mapTomEntity() = MovieEntity(
//    page = page,
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath.orEmpty(),
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity,
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    title = this.title.orEmpty(),
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun List<MovieResponseDto>.mapToEntity(): List<MovieEntity> {
    return this.map {
        it.mapTomEntity()
    }
}



fun MovieResponseDto.mapFromEntity() = Movie(
//    page = page,
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath.orEmpty(),
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity,
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    title = this.title.orEmpty(),
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun Movie.mapFromDomain() = MovieResponseDto(
//    page = page,
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun List<MovieResponseDto>.mapFromListModel(): List<Movie> {
    return this.map {
        it.mapFromEntity()
    }
}

fun List<Movie>.mapFromListDomain(): List<MovieResponseDto> {
    return this.map {
        it.mapFromDomain()
    }
}




