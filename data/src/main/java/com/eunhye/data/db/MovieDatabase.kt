package com.eunhye.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eunhye.data.model.search.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}