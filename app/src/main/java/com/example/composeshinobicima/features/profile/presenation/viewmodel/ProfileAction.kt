package com.example.composeshinobicima.features.profile.presenation.viewmodel

import com.example.composeshinobicima.appcore.mvi.Action

sealed class ProfileAction:Action {

    object GetAccount:ProfileAction()
    object LogOut:ProfileAction()

}