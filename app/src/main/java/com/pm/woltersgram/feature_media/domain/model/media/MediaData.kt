package com.pm.woltersgram.feature_media.domain.model.media

data class MediaData(
    val id: String?,
    val mediaType: String?,
    val mediaUrl: String?,
    val timestamp: String?,
    val username: String?,
    val children: List<ChildrenData>?,
) {
    object MediaType {
        const val IMAGE = "IMAGE"
        const val CAROUSEL_ALBUM = "CAROUSEL_ALBUM"
    }
}