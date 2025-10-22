package com.example.composeshinobicima.features.detail.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.Action
import com.example.composeshinobicima.features.detail.domain.constants.DetailTab

sealed class DetailActions : Action{
    data class SwitchTab(val tab: DetailTab):DetailActions()
    data class GetDetailMovie(val movieId:Int):DetailActions()
    data class GetDetailTv(val seriesId:Int):DetailActions()
    data class GetDetailPerson(val personId:Int):DetailActions()
    data class GetMovieVideo(val movieId:Int):DetailActions()
    data class GetTvVideo(val seriesId: Int):DetailActions()
    data class GetMovieCredits(val movieId: Int):DetailActions()
    data class GetTvCredits(val seriesId: Int):DetailActions()
    data class GetPeopleCredits(val personId: Int):DetailActions()
    data class GetMovieSimilar(val movieId: Int):DetailActions()
    data class GetTvSimilar(val seriesId: Int):DetailActions()
    data class GetMovieReviews(val movieId: Int):DetailActions()
    data class GetTvReviews(val seriesId: Int):DetailActions()
}



