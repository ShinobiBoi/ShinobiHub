package com.example.composeshinobicima.features.find.presenation.viewmodel

import com.example.composeshinobicima.appcore.data.model.genre.Genre
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.ViewState

data class FindViewState (
    val genres: CommonViewState<List<Genre>> = CommonViewState(),
    val mediaType: CommonViewState<MediaType> = CommonViewState(data = MediaType.All),
    val query: CommonViewState<String> = CommonViewState(data = ""),
    val media:MediaViewState = CommonViewState()
) :ViewState