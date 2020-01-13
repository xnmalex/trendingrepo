package com.gojek.assignment.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.gojek.assignment.data.database.AppDatabase
import com.gojek.assignment.ui.main.TrendingRepoListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrendingRepoListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "trendingRepos").build()
            @Suppress("UNCHECKED_CAST")
            return TrendingRepoListViewModel(db.repositoriesDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}