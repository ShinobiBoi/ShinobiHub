package com.example.composeshinobicima.features.favourite.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.Result

sealed class FavouriteResult():Result<FavouriteViewState> {


    data class MediaLoaded(val media:MediaViewState): FavouriteResult() {
        override fun reduce(
            defaultState: FavouriteViewState,
            oldState: FavouriteViewState
        ): FavouriteViewState {
            return oldState.copy(
                media = media
            )
        }
    }

        data class ChangeMediaType(val mediaType: MediaType):FavouriteResult(){
            override fun reduce(
                defaultState: FavouriteViewState,
                oldState: FavouriteViewState
            ): FavouriteViewState {
                return oldState.copy(
                    mediaType = mediaType
                )
            }
        }
}