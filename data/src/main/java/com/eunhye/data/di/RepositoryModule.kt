package com.eunhye.data.di

import com.eunhye.data.repository.MovieRepositoryImpl
import com.eunhye.data.repository.login.LoginRepositoryImpl
import com.eunhye.data.repository.login.local.LoginLocalDataSource
import com.eunhye.data.repository.search.local.MovieLocalDataSource
import com.eunhye.data.repository.search.remote.MovieRemoteDataSource
import com.eunhye.domain.repository.LoginRepository
import com.eunhye.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(loginLocalDataSource: LoginLocalDataSource): LoginRepository {
        return LoginRepositoryImpl(loginLocalDataSource)
    }
}