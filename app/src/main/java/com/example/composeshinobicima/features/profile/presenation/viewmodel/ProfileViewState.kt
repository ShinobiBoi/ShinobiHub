package com.example.composeshinobicima.features.profile.presenation.viewmodel

import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.ViewState
import com.example.composeshinobicima.features.home.data.model.account.AccountResponse

data class ProfileViewState (
    val account:CommonViewState<AccountResponse> = CommonViewState(),
):ViewState