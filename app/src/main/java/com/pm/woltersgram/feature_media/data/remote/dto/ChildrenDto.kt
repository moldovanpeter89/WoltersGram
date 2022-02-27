package com.pm.woltersgram.feature_media.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ChildrenDto(
    @SerializedName("data")
    val data: List<ChildrenDataDto>?,
)