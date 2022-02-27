package com.pm.woltersgram.feature_media.data.remote.apiservice

import com.pm.woltersgram.feature_media.data.remote.dto.MediaDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaApiService {

    @GET("/me/media")
    suspend fun getUserMedia(
        @Query("access_token") accessToken: String?,
        @Query("fields") fields: String?,
        @Query("after") cursorAfter: String?,
        @Query("limit") numberOfItemsToLoad: Int?
    ): MediaDto
}