package com.eunhye.data.mapper

import com.eunhye.data.model.search.MovieEntity
import com.eunhye.domain.model.search.Movie

fun mapperToMovie(movies: List<MovieEntity>): List<Movie> {
    return movies.toList().map {
        Movie(
            it.actor,
            it.director,
            it.image,
            it.link,
            it.pubDate,
            it.subtitle,
            it.title,
            it.userRating
        )
    }
}