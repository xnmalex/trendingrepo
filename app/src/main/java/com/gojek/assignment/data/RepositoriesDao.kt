package com.gojek.assignment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RepositoriesDao {
    @get:Query("SELECT * FROM repositories")
    val all: List<Repositories>

    @Insert
    fun insertAll(vararg repositories: Repositories)
}