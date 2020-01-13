package com.gojek.assignment.base

import androidx.lifecycle.ViewModel
import com.gojek.assignment.injection.component.DaggerViewModelInjector
import com.gojek.assignment.injection.component.ViewModelInjector
import com.gojek.assignment.module.NetworkModule
import com.gojek.assignment.ui.main.TrendingRepoItemViewModel
import com.gojek.assignment.ui.main.TrendingRepoListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is TrendingRepoListViewModel -> injector.inject(this)
            is TrendingRepoItemViewModel -> injector.inject(this)
        }
    }
}