package com.example.composeshinobicima.features.login.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.Result
import com.example.composeshinobicima.features.login.data.model.login.LoginResponse
import com.example.composeshinobicima.features.login.data.model.token.TokenResponse

sealed class LoginResult :Result<LoginViewState>{

    data class RequestToken(val state: CommonViewState<TokenResponse>):LoginResult(){
        override fun reduce(
            defaultState: LoginViewState,
            oldState: LoginViewState
        ): LoginViewState {
            return oldState.copy(
                requestToken = state
            )
        }
    }

    data class Login(val state: CommonViewState<LoginResponse>):LoginResult(){
        override fun reduce(
            defaultState: LoginViewState,
            oldState: LoginViewState
        ): LoginViewState {
            return oldState.copy(
                loginResponse = state
            )
        }
    }

    data class SessionCreated(val state: CommonViewState<String>):LoginResult() {
        override fun reduce(
            defaultState: LoginViewState,
            oldState: LoginViewState
        ): LoginViewState {
            return oldState.copy(
                sessionId = state
            )
        }
    }

}