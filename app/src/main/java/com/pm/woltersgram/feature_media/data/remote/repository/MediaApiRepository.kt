package com.pm.woltersgram.feature_media.data.remote.repository

import com.pm.woltersgram.feature_media.data.mapper.MediaMapper
import com.pm.woltersgram.feature_media.data.remote.apiservice.MediaApiService
import com.pm.woltersgram.feature_media.domain.model.media.Media
import com.pm.woltersgram.feature_media.domain.repository.MediaRepository

class MediaApiRepository(
    private val mediaApiService: MediaApiService,
    private val mediaMapper: MediaMapper
) : MediaRepository {

    companion object {
        //usually it's not here..
        const val ACCESS_TOKEN =
            "IGQVJVQnhZAX1lPUG1IU3VNTFNnWm1EUXBGMU4wT1ZALRmYyTVdDNGFqczR1YkU0b19fUzFqY05zVU9GZAXZA1d0cwdTduNG1VZAWRRdC1rSGU0YzdZAVzM1VnZAfeHlXUmh4OFBRcFRZAT2djazlSMGg4SV9XWAZDZD"
    }

    override suspend fun getUserMedia(
        fields: String?,
        cursorAfter: String?,
        numberOfItemsToLoad: Int?
    ): Media {
        val mediaDto =
            mediaApiService.getUserMedia(ACCESS_TOKEN, fields, cursorAfter,numberOfItemsToLoad)
        return mediaMapper.mediaDtoToMediaModel(mediaDto)
    }
}