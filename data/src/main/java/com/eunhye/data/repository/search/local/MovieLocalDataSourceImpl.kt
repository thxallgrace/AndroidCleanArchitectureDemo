package com.eunhye.data.repository.search.local

import com.eunhye.data.db.MovieDao
import com.eunhye.data.model.search.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun insertMovies(movies: List<MovieEntity>): Completable = movieDao.insertMovies(movies)

    override fun getAllMovies(): Single<List<MovieEntity>> = movieDao.getAllMovies()

    override fun getSearchMovies(title: String): Single<List<MovieEntity>> = movieDao.getMoviesByTitle(title)

    override fun deleteAllMovies(): Completable = movieDao.deleteAllMovies()
}