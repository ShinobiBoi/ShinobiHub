package com.example.composeshinobicima.features.detail.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.Result
import com.example.composeshinobicima.features.detail.data.model.credits.CreditsResponse
import com.example.composeshinobicima.features.detail.data.model.video.VideoItem
import com.example.composeshinobicima.features.detail.domain.constants.DetailTab
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem

sealed class DetailResults : Result<DetailViewState> {


    data class Loading(val state: Boolean) :DetailResults(){
        override fun reduce(
            defaultState: DetailViewState,
            oldState: DetailViewState
        ): DetailViewState {
            return oldState.copy(
                isLoading = state
            )
        }

    }


    data class SwitchTab(val state: CommonViewState<DetailTab>) : DetailResults() {
        override fun reduce(
            defaultState: DetailViewState,
            oldState: DetailViewState
        ): DetailViewState {
            return oldState.copy(
                selectedTab = state
            )
        }

    }

    data class DetailMediaLoaded(val state: CommonViewState<DetailMediaItem>) : DetailResults() {
        override fun reduce(
            defaultState: DetailViewState, oldState: DetailViewState
        ): DetailViewState {
            return oldState.copy(
                detailMediaItem = state
            )
        }

    }

    data class VideoList(val state: CommonViewState<List<VideoItem>>) : DetailResults() {
        override fun reduce(
            defaultState: DetailViewState,
            oldState: DetailViewState
        ): DetailViewState {
            return oldState.copy(
                videoList = state
            )
        }

    }

    data class CreditsLoad(val state: CommonViewState<CreditsResponse>) : DetailResults(){
        override fun reduce(
            defaultState: DetailViewState,
            oldState: DetailViewState
        ): DetailViewState {
            return oldState.copy(
                credits = state
            )
        }

    }

    data class SimilarLoad(val state: MediaViewState) : DetailResults(){
        override fun reduce(
            defaultState: DetailViewState,
            oldState: DetailViewState
        ): DetailViewState {
            return oldState.copy(
                similar = state
            )
        }

    }




}