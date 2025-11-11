package com.example.composeshinobicima.features.profile.domain.remote

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.login.data.model.session.SessionResponse
import com.example.composeshinobicima.features.profile.data.model.DeleteSessionRequest


interface ProfileRemoteClient {

    suspend fun deleteSession(body: DeleteSessionRequest): DataState<SessionResponse>


}