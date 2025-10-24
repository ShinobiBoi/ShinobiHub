package com.example.composeshinobicima.features.splash.presentation.viewmodel

import com.example.composeshinobicima.appcore.mvi.Action

sealed class SplashAction:Action {

    object GetSessionId:SplashAction()
}