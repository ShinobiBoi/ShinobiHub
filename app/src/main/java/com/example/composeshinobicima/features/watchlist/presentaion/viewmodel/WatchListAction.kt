package com.example.composeshinobicima.features.watchlist.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.Action

sealed class WatchListAction :Action{

    object GetMovieWatchList:WatchListAction()
    object GetTvWatchList:WatchListAction()
    data class ChangeMediaType(val mediaType: MediaType):WatchListAction()

}