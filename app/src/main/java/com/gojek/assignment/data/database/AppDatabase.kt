package com.gojek.assignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gojek.assignment.data.Repositories
import com.gojek.assignment.data.RepositoriesDao

@Database(entities = arrayOf(Repositories::class), version = 1 )
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoriesDao(): RepositoriesDao
}