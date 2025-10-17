package com.example.composeshinobicima.features.home.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.Action


sealed class HomeAction : Action {
    data class ChangeMediaType(val type: MediaType) : HomeAction()
    object GetTrendingAll : HomeAction()
    object GetTrendingMovies : HomeAction()
    object GetTrendingTv : HomeAction()
    object GetTrendingPeople : HomeAction()
    object GetPopularMovies : HomeAction()
    object GetTopRatedMovies : HomeAction()
    object GetUpComingMovies : HomeAction()
    object GetOnTheAirTv : HomeAction()
    object GetPopularTv : HomeAction()
    object GetTopRatedTv : HomeAction()
}