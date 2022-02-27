package com.pm.woltersgram.feature_media.di

import com.pm.woltersgram.feature_media.data.mapper.MediaMapper
import com.pm.woltersgram.feature_media.data.remote.apiservice.MediaApiService
import com.pm.woltersgram.feature_media.data.remote.repository.MediaApiRepository
import com.pm.woltersgram.feature_media.domain.repository.MediaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMediaRepository(
        mediaApiService: MediaApiService,
        mediaMapper: MediaMapper
    ): MediaRepository {
        return MediaApiRepository(
            mediaApiService = mediaApiService,
            mediaMapper = mediaMapper
        )
    }
}