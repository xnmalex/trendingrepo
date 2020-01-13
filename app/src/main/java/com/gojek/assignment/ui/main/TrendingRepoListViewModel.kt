package com.gojek.assignment.ui.main

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.gojek.assignment.base.BaseViewModel
import com.gojek.assignment.data.Repositories
import com.gojek.assignment.data.RepositoriesDao
import com.gojek.assignment.network.TrendingRepoAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrendingRepoListViewModel(private val repositoriesDao: RepositoriesDao) : BaseViewModel() {
    @Inject
    lateinit var trendingRepoApi: TrendingRepoAPI

    val trendingRepoListAdapter: TrendingRepoListAdapter = TrendingRepoListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val isLoading: ObservableBoolean =  ObservableBoolean()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadTrendingRepos() }

    private lateinit var subscription: Disposable

    init{
        loadTrendingRepos()
    }

    fun loadTrendingRepos(){
        subscription = Observable.fromCallable { repositoriesDao.all }
            .concatMap {
                    dbRepoList ->
                if(dbRepoList.isEmpty())
                    trendingRepoApi.getTrendingRepo().concatMap {
                            apiPostList -> repositoriesDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(dbRepoList)
                    }
                else
                    Observable.just(dbRepoList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveRepoListStart() }
            .doOnTerminate { onRetrieveRepoListFinish() }
            .subscribe(
                { result -> onRetrieveRepoListSuccess(result) },
                { throwable ->onRetrievePostListError( throwable) }
            )
    }

    private fun onRetrieveRepoListStart(){
        loadingVisibility.value = View.VISIBLE
        isLoading.set(true)
    }

    private fun onRetrieveRepoListFinish(){
        loadingVisibility.value = View.GONE
        isLoading.set(false)
    }

    private fun onRetrieveRepoListSuccess(repoList:List<Repositories>){
        Log.v("debug","size"+ repoList.size)
        trendingRepoListAdapter.updateRepoList(repoList)
    }

    private fun onRetrievePostListError(error:Throwable){
        Log.v("debug","error"+error.localizedMessage)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}

