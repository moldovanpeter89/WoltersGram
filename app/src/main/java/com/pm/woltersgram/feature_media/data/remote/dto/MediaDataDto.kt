package com.pm.woltersgram.feature_media.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MediaDataDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("media_type")
    val mediaType: String?,

    @SerializedName("media_url")
    val mediaUrl: String?,

    @SerializedName("timestamp")
    val timestamp: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("children")
    val children: ChildrenDto?,
)