package com.example.composeshinobicima.features.profile.presenation.viewmodel

import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.Result
import com.example.composeshinobicima.features.home.data.model.account.AccountResponse

sealed class ProfileResult ():Result<ProfileViewState>{

    data class AccountLoaded(val account: CommonViewState<AccountResponse>) : ProfileResult(){
        override fun reduce(
            defaultState: ProfileViewState,
            oldState: ProfileViewState
        ): ProfileViewState {
            return oldState.copy(
                account = account
            )
        }
    }

    data class NotificationToggle(val notification: Boolean) : ProfileResult(){
        override fun reduce(
            defaultState: ProfileViewState,
            oldState: ProfileViewState
        ): ProfileViewState {
            return oldState.copy(
                notification = notification
            )
        }

    }

    data class LoggedOut(val loggedOut: CommonViewState<Boolean>) : ProfileResult(){
        override fun reduce(
            defaultState: ProfileViewState,
            oldState: ProfileViewState
        ): ProfileViewState {
            return oldState.copy(
                loggedOut = loggedOut
            )
        }
    }

}