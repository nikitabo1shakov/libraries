package com.nikitabolshakov.libraries.data

import com.nikitabolshakov.libraries.data.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}