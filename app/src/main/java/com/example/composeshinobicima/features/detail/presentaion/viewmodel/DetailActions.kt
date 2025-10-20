package com.example.composeshinobicima.features.detail.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.Action

sealed class DetailActions : Action{

    data class GetDetailMovie(val movieId:Int):DetailActions()
    data class GetDetailTv(val seriesId:Int):DetailActions()
    data class GetDetailPerson(val personId:Int):DetailActions()
}