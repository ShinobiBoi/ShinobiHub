package com.example.composeshinobicima.features.profile.presenation.viewmodel

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.composeshinobicima.TrendingWorker
import com.example.composeshinobicima.appcore.data.local.SessionManager
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.mvi.CommonViewState
import com.example.composeshinobicima.appcore.mvi.MVIBaseViewModel
import com.example.composeshinobicima.features.home.domain.usecase.GetAccountUseCase
import com.example.composeshinobicima.features.profile.data.model.DeleteSessionRequest
import com.example.composeshinobicima.features.profile.domain.usecase.DeleteSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase,
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


                val sessionId=sessionManager.getSessionId().firstOrNull()
                if (sessionId!=null){

                    when(val result=deleteSessionUseCase(DeleteSessionRequest(sessionId))){

                        is DataState.Success->{
                            emit(ProfileResult.LoggedOut(CommonViewState(isSuccess = true, data = result.data)))
                            sessionManager.clearSession()
                        }
                        is DataState.Error->{
                            emit(ProfileResult.LoggedOut(CommonViewState(errorThrowable = result.throwable)))
                        }
                        else ->{
                        }
                    }
                }else{
                    emit(ProfileResult.LoggedOut(CommonViewState(isSuccess = true, data = true)))
                    sessionManager.clearSession()
                }
            }
            is ProfileAction.GetNotifications->{
                handleToggleNotifications(this,sessionManager.getNotification().firstOrNull(),action.context)
            }

            is ProfileAction.ToggleNotifications->{
                handleToggleNotifications(this,action.notification,action.context)
                sessionManager.saveNotification(action.notification)
            }
        }
    }

    private suspend fun handleToggleNotifications(
        flowCollector: FlowCollector<ProfileResult>,
        notification: Boolean?,
        context: Context
    ) {
        if (notification!=null){
            flowCollector.emit(ProfileResult.NotificationToggle(notification))

            if (notification){

                val periodicRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<TrendingWorker>(
                    12, TimeUnit.HOURS
                ).build()

                WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                    "TrendingWorker", // unique name
                    ExistingPeriodicWorkPolicy.KEEP, // if already exists, keep existing
                    periodicRequest
                )


            }else{
                WorkManager.getInstance(context).cancelUniqueWork("trending_job")
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