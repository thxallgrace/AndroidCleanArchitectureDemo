package com.eunhye.data.repository.search.local

import com.eunhye.data.model.search.MovieEntity
import com.eunhye.data.model.search.MovieResponse
import com.eunhye.domain.model.search.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieLocalDataSource {
    fun insertMovies(movies: List<MovieEntity>): Completable
    fun getAllMovies(): Single<List<MovieEntity>>
    fun getSearchMovies(title: String): Single<List<MovieEntity>>
    fun deleteAllMovies(): Completable
}