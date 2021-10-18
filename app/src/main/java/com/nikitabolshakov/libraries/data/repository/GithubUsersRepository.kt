package com.nikitabolshakov.libraries.data.repository

import com.nikitabolshakov.libraries.data.model.GithubUser

class GithubUsersRepository {

    private val repository = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() = repository
}