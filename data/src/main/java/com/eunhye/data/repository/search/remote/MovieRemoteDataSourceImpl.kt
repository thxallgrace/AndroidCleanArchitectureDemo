package com.eunhye.data.repository.search.remote

import com.eunhye.data.api.ApiInterface
import com.eunhye.data.model.search.MovieResponse
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) : MovieRemoteDataSource {
    override fun getSearchMovies(query: String, start: Int): Single<MovieResponse> {
        return apiInterface.getSearchMovie(query, start)
    }
}