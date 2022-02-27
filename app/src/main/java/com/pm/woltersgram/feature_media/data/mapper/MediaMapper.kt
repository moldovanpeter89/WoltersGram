package com.pm.woltersgram.feature_media.data.mapper

import com.pm.woltersgram.feature_media.data.remote.dto.MediaDto
import com.pm.woltersgram.feature_media.domain.model.media.*
import javax.inject.Inject

class MediaMapper @Inject constructor() {

    fun mediaDtoToMediaModel(mediaDto: MediaDto?): Media {
        val mediaDataList = mediaDto?.data?.map { mediaDataDto ->
            with(mediaDataDto) {
                MediaData(
                    id = id,
                    mediaType = mediaType,
                    mediaUrl = mediaUrl,
                    timestamp = timestamp,
                    username = username,
                    children = children?.data?.map { childrenDataDto ->
                        ChildrenData(
                            id = childrenDataDto.id,
                            mediaType = childrenDataDto.mediaType,
                            mediaUrl = childrenDataDto.mediaUrl,
                        )
                    }
                )
            }
        }

        return Media(
            mediaDataList = mediaDataList,
            paging = Paging(
                cursors = Cursors(
                    after = mediaDto?.paging?.cursors?.after
                ),
                next = mediaDto?.paging?.next
            )
        )
    }
}