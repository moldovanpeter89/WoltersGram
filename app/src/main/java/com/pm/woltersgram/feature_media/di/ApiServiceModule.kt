package com.pm.woltersgram.feature_media.di

import com.pm.woltersgram.feature_media.data.remote.apiservice.MediaApiService
import com.pm.woltersgram.feature_media.di.qualifiers.InstaGraphRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun providesApplicationApiService(@InstaGraphRetrofit retrofit: Retrofit): MediaApiService {
        return retrofit.create(MediaApiService::class.java)
    }
}