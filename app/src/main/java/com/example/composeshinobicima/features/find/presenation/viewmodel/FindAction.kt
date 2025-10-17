package com.example.composeshinobicima.features.find.presenation.viewmodel

import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.mvi.Action

sealed class FindAction():Action {
    data class ChangeMediaType(val type: MediaType) : FindAction()
    object GetTrendingAll:FindAction()
    object GetTrendingMovies:FindAction()
    object GetTrendingTv:FindAction()
    object GetTrendingPeople:FindAction()
}