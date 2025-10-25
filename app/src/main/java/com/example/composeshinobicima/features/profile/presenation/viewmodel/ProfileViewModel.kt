package com.example.composeshinobicima.features.profile.presenation.viewmodel

import com.example.composeshinobicima.appcore.data.local.SessionManager
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MVIBaseViewModel
import com.example.composeshinobicima.features.home.domain.usecase.GetAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val sessionManager: SessionManager
) :MVIBaseViewModel<ProfileAction, ProfileResult, ProfileViewState>(){
    override val defaultViewState: ProfileViewState
        get() = ProfileViewState()

    override fun handleAction(action: ProfileAction): Flow<ProfileResult> = flow{

        when(action){

            is ProfileAction.GetAccount->{
                handleGetAccount(this,sessionManager.getSessionId().firstOrNull())
            }

            is ProfileAction.LogOut->{
                sessionManager.clearSession()
            }
        }
    }

    private suspend fun handleGetAccount(
        flowCollector: FlowCollector<ProfileResult>,
        sessionId: String?
    ) {
        if (sessionId!=null)
            when (val result = getAccountUseCase(sessionId)) {
                is DataState.Success -> {
                    flowCollector.emit(ProfileResult.AccountLoaded(CommonViewState(data = result.data, isSuccess = true)))
                }
                is DataState.Error -> flowCollector.emit(
                    ProfileResult.AccountLoaded(CommonViewState(errorThrowable = result.throwable))
                )
                is DataState.Empty -> flowCollector.emit(
                    ProfileResult.AccountLoaded(CommonViewState(isEmpty = true))
                )
                else -> {}
            }
    }
}