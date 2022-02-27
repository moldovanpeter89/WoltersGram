package com.pm.woltersgram.feature_media.domain.use_case

import com.pm.woltersgram.core.util.Resource
import com.pm.woltersgram.core.domain.error.ErrorCodes
import com.pm.woltersgram.feature_media.domain.model.media.Media
import com.pm.woltersgram.feature_media.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

class GetUserMedia(
    private val mediaRepository: MediaRepository
) {
    private val fields =
        "id,username,media_type,timestamp,media_url,children{id,media_type,media_url}"

    suspend fun execute(params: Params?): Flow<Resource<Media>> {
        return flow {
            emit(Resource.Loading())

            val media = mediaRepository.getUserMedia(
                fields = fields,
                cursorAfter = params?.cursorAfter,
                numberOfItemsToLoad = params?.numberOfItemsToLoad
            )

            emit(Resource.Success(media))

        }.catch { exception ->
//            //we don't want to catch the cancellation of the Job.
            if (exception is CancellationException) {
                throw CancellationException("Job was cancelled.")
            }
            val errorCode = when (exception) {
                is UnknownHostException -> ErrorCodes.ERROR_NO_INTERNET
                is SocketTimeoutException -> ErrorCodes.ERROR_TIME_OUT
                else -> ErrorCodes.ERROR_GENERAL
            }
            emit(
                Resource.Error(
                    errorCode = errorCode,
                )
            )
        }
    }

    data class Params(val cursorAfter: String?, val numberOfItemsToLoad: Int?)
}