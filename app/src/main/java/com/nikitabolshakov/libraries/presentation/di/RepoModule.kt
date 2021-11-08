package com.nikitabolshakov.libraries.presentation.di

import com.nikitabolshakov.libraries.data.IDataSource
import com.nikitabolshakov.libraries.data.repository.IGithubUsersRepo
import com.nikitabolshakov.libraries.data.repository.RetrofitGithubUsersRepo
import com.nikitabolshakov.libraries.data.room.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun usersRepo(api: IDataSource, dao: UserDao): IGithubUsersRepo =
        RetrofitGithubUsersRepo(
            api, dao
        )
}