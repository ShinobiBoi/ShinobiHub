package com.example.composeshinobicima.features.login.domain.repo

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.login.data.model.login.LoginRequest
import com.example.composeshinobicima.features.login.data.model.session.SessionRequest

interface LoginRepo {

    suspend fun login(loginRequest: LoginRequest): DataState<Boolean>

    suspend fun createToken():DataState<String>

    suspend fun createSession(sessionRequest: SessionRequest): DataState<String>
}