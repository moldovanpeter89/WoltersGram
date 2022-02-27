package com.pm.woltersgram.feature_media.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MediaDto(
    @SerializedName("data")
    val data: List<MediaDataDto>?,

    @SerializedName("paging")
    val paging: PagingDto?
)