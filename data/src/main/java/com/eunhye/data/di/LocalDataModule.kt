package com.eunhye.data.di

import android.content.Context
import androidx.room.Room
import com.eunhye.data.db.MovieDao
import com.eunhye.data.db.MovieDatabase
import com.eunhye.data.repository.login.local.LoginLocalDataSource
import com.eunhye.data.repository.login.local.LoginLocalDataSourceImpl
import com.eunhye.data.repository.search.local.MovieLocalDataSource
import com.eunhye.data.repository.search.local.MovieLocalDataSourceImpl
import com.eunhye.data.utils.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {

    //localRepo
    @Provides
    @Singleton
    fun provideLoginLocalDataSource(preferenceManager: PreferenceManager): LoginLocalDataSource {
        return LoginLocalDataSourceImpl(preferenceManager)
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    //room
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "Movie.db"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

    // sharedPref
    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManager(context)
    }
}