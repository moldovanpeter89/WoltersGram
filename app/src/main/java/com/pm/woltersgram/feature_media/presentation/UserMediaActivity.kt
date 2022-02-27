package com.pm.woltersgram.feature_media.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pm.woltersgram.core.util.ErrorHandler
import com.pm.woltersgram.core.util.collectLatestLifecycleFlow
import com.pm.woltersgram.core.util.collectLifecycleFlow
import com.pm.woltersgram.databinding.ActivityUserMediaBinding
import com.pm.woltersgram.feature_media.presentation.adapter.UserMediaAdapter
import com.pm.woltersgram.feature_media.presentation.state.UserMediaState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserMediaActivity : AppCompatActivity() {

    private val mediaViewModel: MediaViewModel by viewModels()

    private lateinit var binding: ActivityUserMediaBinding
    private var userMediaAdapter = UserMediaAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initEndlessScrolling()
        collectEvent()
        collectState()
    }

    private fun initView() {
        binding.userMediaListView.apply {
            adapter = userMediaAdapter
            layoutManager =
                LinearLayoutManager(this@UserMediaActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initEndlessScrolling() {
        val layoutManager = binding.userMediaListView.layoutManager
        if (layoutManager != null) {
            layoutManager as LinearLayoutManager
            binding.userMediaListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollHorizontally(1) && recyclerView.canScrollVertically(-1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        mediaViewModel.loadMore()
                    }
                }
            })
        }
    }

    private fun collectState() {
        collectLatestLifecycleFlow(mediaViewModel.stateFlow) { userMediaState ->
            updateUi(userMediaState)
        }
    }

    private fun updateUi(userMediaState: UserMediaState) {
        userMediaAdapter.submitList(userMediaState.mediaDataList)
        binding.progressBar.root.visibility =
            if (userMediaState.isContentLoading) View.VISIBLE else View.GONE
        binding.loadMoreProgressBar.root.visibility =
            if (userMediaState.isLoadMoreLoading) View.VISIBLE else View.GONE
    }

    private fun collectEvent() {
        collectLifecycleFlow(mediaViewModel.uiEventFlow) { event ->
            when (event) {
                is MediaViewModel.UiEvent.ShowError -> {
                    Snackbar.make(
                        binding.root,
                        ErrorHandler.getErrorMessage(this, event.errorCode),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
