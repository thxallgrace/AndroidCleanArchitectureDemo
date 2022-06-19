package com.eunhye.domain.di

import com.eunhye.domain.repository.LoginRepository
import com.eunhye.domain.repository.MovieRepository
import com.eunhye.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetLocalMoviesUseCase(movieRepository: MovieRepository): GetLocalMoviesUseCase {
        return GetLocalMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetLoginUseCase(loginRepository: LoginRepository): GetLoginUseCase {
        return GetLoginUseCase(loginRepository)
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetPagingMoviesUseCase(movieRepository: MovieRepository): GetPagingMoviesUseCase {
        return GetPagingMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideInsertLoginUseCase(loginRepository: LoginRepository): InsertLoginUseCase {
        return InsertLoginUseCase(loginRepository)
    }

}