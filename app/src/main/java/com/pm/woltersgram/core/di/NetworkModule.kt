package com.pm.woltersgram.core.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val READ_TIME_OUT = 30L
    private const val CONNECT_TIME_OUT = 30L

    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        okHttpBuilder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)

        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun providesGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    fun providesDefaultRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}