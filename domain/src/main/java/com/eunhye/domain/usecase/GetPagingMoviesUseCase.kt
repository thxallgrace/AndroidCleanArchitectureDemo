package com.eunhye.domain.usecase

import com.eunhye.domain.model.search.Movie
import com.eunhye.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPagingMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    fun execute(
        query: String,
        offset: Int
    ): Single<List<Movie>> = repository.getPagingMovies(query, offset)
}