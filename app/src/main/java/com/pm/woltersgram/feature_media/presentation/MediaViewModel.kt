package com.pm.woltersgram.feature_media.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pm.woltersgram.core.util.Resource
import com.pm.woltersgram.feature_media.domain.model.media.Media
import com.pm.woltersgram.feature_media.domain.model.media.MediaData
import com.pm.woltersgram.feature_media.domain.use_case.GetUserMedia
import com.pm.woltersgram.feature_media.presentation.state.UserMediaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val getUserMedia: GetUserMedia
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(UserMediaState())
    val stateFlow = _stateFlow.asStateFlow()

    private val _uiEventFlow = MutableSharedFlow<UiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var numberOfItemsToLoad = 2
    private var cursorAfter: String? = ""
    private var pagingNext: String? = ""
    private val allUserMediaList = mutableListOf<MediaData>()

    init {
        loadUserMedia()
    }

    private fun loadUserMedia() {
        viewModelScope.launch {
            getUserMedia.execute(GetUserMedia.Params(cursorAfter, numberOfItemsToLoad))
                .onEach {
                    checkResult(it)
                }
                .launchIn(this)
        }
    }

    fun loadMore() {
        if (pagingNext.isNullOrEmpty()) {
            return
        }
        loadUserMedia()
    }

    private suspend fun checkResult(result: Resource<Media>) {
        when (result) {
            is Resource.Success -> {
                cursorAfter = result.data?.paging?.cursors?.after
                pagingNext = result.data?.paging?.next

                val resultList = result.data?.mediaDataList ?: emptyList()
                allUserMediaList.addAll(resultList)

                _stateFlow.value = stateFlow.value.copy(
                    mediaDataList = allUserMediaList.toList(),
                    isContentLoading = false,
                    isLoadMoreLoading = false
                )
            }
            is Resource.Error -> {
                _stateFlow.value = stateFlow.value.copy(
                    mediaDataList = arrayListOf(),
                    isContentLoading = false,
                    isLoadMoreLoading = false
                )
                result.errorCode?.let { errorCode ->
                    showError(errorCode)
                }
            }
            is Resource.Loading -> {
                _stateFlow.value = stateFlow.value.copy(
                    //if there is no paging next then it's content loading
                    isContentLoading = pagingNext.isNullOrEmpty(),
                    //if we have cursorAfter then it's load more loading
                    isLoadMoreLoading = cursorAfter.isNullOrEmpty().not()
                )
            }
        }
    }

    suspend fun showError(errorCode: Int) {
        _uiEventFlow.emit(
            UiEvent.ShowError(
                errorCode
            )
        )
    }

    sealed class UiEvent {
        data class ShowError(val errorCode: Int) : UiEvent()
    }
}