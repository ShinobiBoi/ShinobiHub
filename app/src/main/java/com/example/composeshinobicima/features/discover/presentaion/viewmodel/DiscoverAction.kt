package com.example.composeshinobicima.features.discover.presentaion.viewmodel

import com.example.composeshinobicima.appcore.data.model.genre.Genre
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.Action
import com.example.composeshinobicima.features.find.presenation.viewmodel.FindAction

sealed class DiscoverAction : Action {

    data class GetDiscoverMovie(val genreId: String) : DiscoverAction()
    data class GetDiscoverTv(val genreId: String) : DiscoverAction()
    data class ChangeMediaType(val type: MediaType) : DiscoverAction()
    object GetGenreList:DiscoverAction()
    data class ToggleGenre(val genre: Genre):DiscoverAction()



}