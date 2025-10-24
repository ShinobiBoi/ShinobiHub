package com.example.composeshinobicima.features.login.presentaion.viewmodel

import android.adservices.ondevicepersonalization.RequestToken
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.ViewState

data class LoginViewState(
    val requestToken: CommonViewState<String> = CommonViewState(),
    val sessionId: CommonViewState<String> = CommonViewState(),
    val isLoginSuccess: CommonViewState<Boolean> = CommonViewState()
):ViewState