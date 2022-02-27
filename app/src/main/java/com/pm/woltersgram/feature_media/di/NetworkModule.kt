package com.pm.woltersgram.feature_media.di

import com.google.gson.Gson
import com.pm.woltersgram.BuildConfig
import com.pm.woltersgram.core.di.NetworkModule.providesDefaultRetrofit
import com.pm.woltersgram.feature_media.di.qualifiers.InstaGraphRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @InstaGraphRetrofit
    @Singleton
    @Provides
    fun providesInstaGraphRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return providesDefaultRetrofit(gson, okHttpClient, BuildConfig.GRAPH_BASE_URL)
    }
}