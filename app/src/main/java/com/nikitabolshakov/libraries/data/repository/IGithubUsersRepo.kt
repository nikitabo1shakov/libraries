package com.nikitabolshakov.libraries.data.repository

import com.nikitabolshakov.libraries.data.model.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Observable<List<GithubUser>>
    fun subscribeOnGithubUsersData(): Observable<List<GithubUser>>
    fun loadUserData()
}