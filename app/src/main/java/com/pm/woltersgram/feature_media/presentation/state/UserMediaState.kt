package com.pm.woltersgram.feature_media.presentation.state

import com.pm.woltersgram.feature_media.domain.model.media.MediaData

data class UserMediaState(
    val mediaDataList: List<MediaData> = emptyList(),
    val isContentLoading: Boolean = false,
    val isLoadMoreLoading: Boolean = false,
)