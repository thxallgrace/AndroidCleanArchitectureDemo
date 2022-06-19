package com.eunhye.domain.usecase

import com.eunhye.domain.model.search.Movie
import com.eunhye.domain.repository.MovieRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetLocalMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    fun execute(
        query: String
    ): Flowable<List<Movie>> = repository.getSearchMovies(query)
}