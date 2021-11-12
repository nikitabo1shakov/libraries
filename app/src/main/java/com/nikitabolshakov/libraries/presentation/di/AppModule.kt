package com.nikitabolshakov.libraries.presentation.di

import com.nikitabolshakov.libraries.data.app.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app1: App) {

    @Provides
    fun app(): App = app1
}