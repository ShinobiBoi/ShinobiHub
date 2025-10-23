package com.example.composeshinobicima.features.discover.presentaion.viewmodel

import com.example.composeshinobicima.appcore.data.model.genre.Genre
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.Result
import com.example.composeshinobicima.features.detail.presentaion.viewmodel.DetailResults
import com.example.composeshinobicima.features.detail.presentaion.viewmodel.DetailViewState

sealed class DiscoverResult :Result<DiscoverViewState>{


    data class Loading(val state: Boolean) : DiscoverResult(){
        override fun reduce(
            defaultState: DiscoverViewState,
            oldState: DiscoverViewState
        ): DiscoverViewState {
            return oldState.copy(
                isLoading = state
            )
        }


    }

    data class GenreList(val state: CommonViewState<List<Genre>>) : DiscoverResult(){
        override fun reduce(
            defaultState: DiscoverViewState,
            oldState: DiscoverViewState
        ): DiscoverViewState {
            return oldState.copy(
                genres = state
            )
        }

    }

    data class Type(val state:MediaType) : DiscoverResult(){
        override fun reduce(
            defaultState: DiscoverViewState,
            oldState: DiscoverViewState
        ): DiscoverViewState {
            return oldState.copy(
                mediaType = state
            )
        }
    }

    data class MediaLoaded(val state: MediaViewState) : DiscoverResult(){
        override fun reduce(
            defaultState: DiscoverViewState,
            oldState: DiscoverViewState
        ): DiscoverViewState {
            return oldState.copy(
                media = state
            )
        }

    }


}