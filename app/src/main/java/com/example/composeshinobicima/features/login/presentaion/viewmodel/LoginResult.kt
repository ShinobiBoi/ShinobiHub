package com.example.composeshinobicima.features.login.presentaion.viewmodel

import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.Result

sealed class LoginResult :Result<LoginViewState>{

    data class RequestToken(val state: CommonViewState<String>):LoginResult(){
        override fun reduce(
            defaultState: LoginViewState,
            oldState: LoginViewState
        ): LoginViewState {
            return oldState.copy(
                requestToken = state
            )
        }
    }

    data class Login(val state: CommonViewState<Boolean>):LoginResult(){
        override fun reduce(
            defaultState: LoginViewState,
            oldState: LoginViewState
        ): LoginViewState {
            return oldState.copy(
                isLoginSuccess = state
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