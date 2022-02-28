package com.pm.woltersgram.feature_media.data

import com.pm.woltersgram.feature_media.domain.model.media.Cursors
import com.pm.woltersgram.feature_media.domain.model.media.Media
import com.pm.woltersgram.feature_media.domain.model.media.MediaData
import com.pm.woltersgram.feature_media.domain.model.media.Paging
import com.pm.woltersgram.feature_media.domain.repository.MediaRepository

class FakeMediaRepository : MediaRepository {

    override suspend fun getUserMedia(
        fields: String?,
        cursorAfter: String?,
        numberOfItemsToLoad: Int?
    ): Media {
        return Media(
            arrayListOf(
                MediaData(
                    "17873556157094888",
                    MediaData.MediaType.IMAGE,
                    "https://scontent.cdninstagram.com/v/t51.2885-15/19954904_829360083896090_1695146759061241856_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=8ae9d6&_nc_ohc=GvkBMh5fHlcAX-qxizn&_nc_ht=scontent.cdninstagram.com&edm=ANo9K5cEAAAA&oh=00_AT87bb2uTjGCkiZ7vAA89nSQGNvGhPCQVEXYJ7ImtyTFlQ&oe=621F63EC",
                    "2017-07-11T17:30:59+0000",
                    "nabmobile",
                    arrayListOf(),
                    )
            ),
            Paging(Cursors("after"), "next")
        )
    }
}