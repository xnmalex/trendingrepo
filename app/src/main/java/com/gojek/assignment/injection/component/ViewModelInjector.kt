package com.gojek.assignment.injection.component

import com.gojek.assignment.base.BaseViewModel
import com.gojek.assignment.module.NetworkModule
import com.gojek.assignment.ui.main.TrendingRepoItemViewModel
import com.gojek.assignment.ui.main.TrendingRepoListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(trendingRepoListViewModel: TrendingRepoListViewModel)

    fun inject(trendingRepoItemViewModel: TrendingRepoItemViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}