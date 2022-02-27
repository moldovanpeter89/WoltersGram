package com.pm.woltersgram.feature_media.di

import com.pm.woltersgram.feature_media.domain.repository.MediaRepository
import com.pm.woltersgram.feature_media.domain.use_case.GetUserMedia
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetUserMediaUseCase(mediaRepository: MediaRepository): GetUserMedia {
        return GetUserMedia(mediaRepository)
    }
}