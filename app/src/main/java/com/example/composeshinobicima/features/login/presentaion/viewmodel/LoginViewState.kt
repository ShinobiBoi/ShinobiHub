package com.example.composeshinobicima.features.login.presentaion.viewmodel

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.ViewState
import com.example.composeshinobicima.features.login.data.model.login.LoginResponse
import com.example.composeshinobicima.features.login.data.model.token.TokenResponse

data class LoginViewState(
    val requestToken: CommonViewState<TokenResponse> = CommonViewState(),
    val sessionId: CommonViewState<String> = CommonViewState(),
    val loginResponse: CommonViewState<LoginResponse> = CommonViewState()
):ViewState