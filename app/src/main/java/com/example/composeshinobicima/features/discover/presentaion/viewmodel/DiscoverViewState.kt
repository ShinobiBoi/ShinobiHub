package com.example.composeshinobicima.features.discover.presentaion.viewmodel

import com.example.composeshinobicima.appcore.data.model.genre.Genre
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.ViewState

data class DiscoverViewState(
    val genres: CommonViewState<List<Genre>> = CommonViewState(),
    val mediaType: CommonViewState<MediaType> = CommonViewState(data = MediaType.All),
    val media:MediaViewState = CommonViewState(),
    val isLoading:Boolean=false
):ViewState