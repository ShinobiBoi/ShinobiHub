package com.example.composeshinobicima.features.detail.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.ViewState
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem

data class DetailViewState (
    val detailMediaItem: CommonViewState<DetailMediaItem> = CommonViewState()
) :ViewState