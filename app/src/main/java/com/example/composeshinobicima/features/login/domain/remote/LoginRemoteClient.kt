package com.example.composeshinobicima.features.login.domain.remote

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.login.data.model.login.LoginRequest
import com.example.composeshinobicima.features.login.data.model.login.LoginResponse
import com.example.composeshinobicima.features.login.data.model.session.SessionRequest
import com.example.composeshinobicima.features.login.data.model.session.SessionResponse
import com.example.composeshinobicima.features.login.data.model.token.TokenResponse


interface LoginRemoteClient {


    suspend fun login(loginRequest:LoginRequest): DataState<LoginResponse>

    suspend fun createToken():DataState<TokenResponse>

    suspend fun createSession(sessionRequest: SessionRequest): DataState<SessionResponse>
}

