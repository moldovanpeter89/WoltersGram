package com.pm.woltersgram.feature_media.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PagingDto(
    @SerializedName("cursors")
    val cursors: CursorsDto?,

    @SerializedName("next")
    val next: String?
)