package com.pm.woltersgram.feature_media.presentation

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.pm.woltersgram.feature_media.domain.model.error.ErrorCodes
import com.pm.woltersgram.feature_media.domain.use_case.GetUserMedia
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MediaViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MediaViewModel

    @Before
    fun setUp() {
        viewModel = MediaViewModel(GetUserMedia(FakeMediaRepository()))
    }

    @Test
    fun `check for list is not empty`() = runBlocking {
        viewModel.stateFlow.test {
            val uiState = awaitItem()
            assertThat(uiState.mediaDataList).isNotEmpty()
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `check for ui event no internet`() = runBlocking {
        val job = launch {
            viewModel.uiEventFlow.test {
                val uiEvent = awaitEvent()
                assertThat(uiEvent).isEqualTo(MediaViewModel.UiEvent.ShowError(ErrorCodes.ERROR_NO_INTERNET))
            }
        }

        viewModel.showError(ErrorCodes.ERROR_NO_INTERNET)

        job.join()
        job.cancel()
    }
}