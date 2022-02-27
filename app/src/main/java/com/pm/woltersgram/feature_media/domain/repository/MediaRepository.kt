package com.pm.woltersgram.feature_media.domain.repository

import com.pm.woltersgram.feature_media.domain.model.media.Media

interface MediaRepository {

    suspend fun getUserMedia(
        fields: String?,
        cursorAfter: String?,
        numberOfItemsToLoad: Int?
    ): Media
}