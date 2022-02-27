package com.pm.woltersgram.feature_media.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ChildrenDataDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("media_type")
    val mediaType: String?,

    @SerializedName("media_url")
    val mediaUrl: String?
)