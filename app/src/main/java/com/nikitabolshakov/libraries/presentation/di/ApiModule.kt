package com.nikitabolshakov.libraries.presentation.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nikitabolshakov.libraries.data.IDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Named("baseUrl")
    fun baseUrl(): String = "https://api.github.com"

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(IDataSource::class.java)
}