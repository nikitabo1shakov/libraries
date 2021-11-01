package com.nikitabolshakov.libraries.data.repository

import com.nikitabolshakov.libraries.data.model.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}