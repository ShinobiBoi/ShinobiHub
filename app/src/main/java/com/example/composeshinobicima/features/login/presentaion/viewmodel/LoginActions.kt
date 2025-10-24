package com.example.composeshinobicima.features.login.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.Action
import com.example.composeshinobicima.features.login.data.model.login.LoginRequest
import com.example.composeshinobicima.features.login.data.model.session.SessionRequest

sealed class LoginActions :Action {
    object GetRequestToken : LoginActions()
    data class Login(val loginRequest: LoginRequest) : LoginActions()
    data class CreateSession(val sessionRequest: SessionRequest) : LoginActions()
    data class SaveSessionId(val sessionId: String) : LoginActions()
    object Logout : LoginActions()
}
