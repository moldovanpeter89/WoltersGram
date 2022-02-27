package com.pm.woltersgram.feature_media.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CursorsDto(
    @SerializedName("after")
    val after: String?,

    @SerializedName("before")
    val before: String?
)