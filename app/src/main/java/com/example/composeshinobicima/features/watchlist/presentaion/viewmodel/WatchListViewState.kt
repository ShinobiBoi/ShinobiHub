package com.example.composeshinobicima.features.watchlist.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.ViewState

data class WatchListViewState (
    val media:MediaViewState = CommonViewState(),
    val mediaType: MediaType=MediaType.Movies
):ViewState