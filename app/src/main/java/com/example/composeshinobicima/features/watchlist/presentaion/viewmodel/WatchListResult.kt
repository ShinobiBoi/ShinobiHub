package com.example.composeshinobicima.features.watchlist.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.Result

sealed class WatchListResult :Result<WatchListViewState>{

    data class MediaLoaded(val media:MediaViewState):WatchListResult(){
        override fun reduce(
            defaultState: WatchListViewState,
            oldState: WatchListViewState
        ): WatchListViewState {
            return oldState.copy(
                media = media
            )
        }
    }

    data class ChangeMediaType(val mediaType: MediaType):WatchListResult(){
        override fun reduce(
            defaultState: WatchListViewState,
            oldState: WatchListViewState
        ): WatchListViewState {
            return oldState.copy(
                mediaType = mediaType
            )
        }
    }


}