package com.gojek.assignment.network

import com.gojek.assignment.data.Repositories
import io.reactivex.Observable
import retrofit2.http.GET

interface TrendingRepoAPI {
    /**
     * Get the list of the pots from the API
     */
    @GET("/repositories")
    fun getTrendingRepo(): Observable<List<Repositories>>
}