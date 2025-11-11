package com.example.composeshinobicima.features.profile.presenation.viewmodel

import android.content.Context
import com.example.composeshinobicima.appcore.mvi.Action

sealed class ProfileAction:Action {
    object GetAccount:ProfileAction()
    object LogOut:ProfileAction()
    data class GetNotifications(val context: Context):ProfileAction()
    data class ToggleNotifications(val notification:Boolean,val context: Context):ProfileAction()
}