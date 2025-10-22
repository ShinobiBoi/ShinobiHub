package com.example.composeshinobicima.features.detail.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MediaViewState
import com.example.composeshinobicima.appcore.mvi.ViewState
import com.example.composeshinobicima.features.detail.data.model.credits.CreditsResponse
import com.example.composeshinobicima.features.detail.data.model.review.Review
import com.example.composeshinobicima.features.detail.data.model.video.VideoItem
import com.example.composeshinobicima.features.detail.domain.constants.DetailTab
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem

data class DetailViewState(
    val selectedTab: CommonViewState<DetailTab> = CommonViewState(data = DetailTab.INFO),
    val detailMediaItem: CommonViewState<DetailMediaItem> = CommonViewState(),
    val videoList:CommonViewState<List<VideoItem>> = CommonViewState(),
    val credits:CommonViewState<CreditsResponse> =CommonViewState(),
    val peopleCredits:MediaViewState =CommonViewState(),
    val similar:MediaViewState =CommonViewState(),
    val review: CommonViewState<List<Review>> =CommonViewState(),
    val isLoading: Boolean = false
) : ViewState