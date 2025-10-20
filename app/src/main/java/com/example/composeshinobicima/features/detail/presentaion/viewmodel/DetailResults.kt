package com.example.composeshinobicima.features.detail.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.Result
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem

sealed class DetailResults : Result<DetailViewState> {

    data class DetailMediaLoaded(val state: CommonViewState<DetailMediaItem>) : DetailResults() {
        override fun reduce(defaultState: DetailViewState, oldState: DetailViewState
        ): DetailViewState {
            return oldState.copy(
                detailMediaItem = state
            )
        }

    }

}