package com.nikitabolshakov.libraries.presentation.di

import androidx.room.Room
import com.nikitabolshakov.libraries.data.app.App
import com.nikitabolshakov.libraries.data.room.AppDatabase
import com.nikitabolshakov.libraries.data.room.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun database(app: App): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "gb-libs.db"
    ).build()

    @Provides
    @Singleton
    fun userDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()
}