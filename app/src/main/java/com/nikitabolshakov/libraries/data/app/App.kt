package com.nikitabolshakov.libraries.data.app

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.nikitabolshakov.libraries.data.room.AppDatabase

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    // Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    private lateinit var db: AppDatabase

    fun getDB(): AppDatabase {
        return db
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "gb-libs.db"
        ).build()
    }
}