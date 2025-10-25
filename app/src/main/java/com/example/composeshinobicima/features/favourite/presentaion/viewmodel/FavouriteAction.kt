package com.example.composeshinobicima.features.favourite.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.Action

sealed class FavouriteAction ():Action {
    object GetMovieFavourite: FavouriteAction()
    object GetTvFavourite: FavouriteAction()
    data class ChangeMediaType(val mediaType: MediaType): FavouriteAction()

}