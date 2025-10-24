package com.example.composeshinobicima.features.splash.presentation.viewmodel

import com.example.composeshinobicima.appcore.data.local.SessionManager
import com.example.composeshinobicima.appcore.mvi.MVIBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    val sessionManager: SessionManager
) : MVIBaseViewModel<SplashAction, SplashResult, SplashViewState>() {

    override val defaultViewState: SplashViewState
        get() = SplashViewState()

    override fun handleAction(action: SplashAction): Flow<SplashResult> = flow {

        when(action){
            is SplashAction.GetSessionId->{
                val sessionId = sessionManager.getSessionId().firstOrNull()
                emit(SplashResult.SessionIdLoad(
                    sessionId
                ))
            }
        }

    }
}