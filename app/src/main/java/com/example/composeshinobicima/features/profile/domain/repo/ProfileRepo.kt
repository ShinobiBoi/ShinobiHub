package com.example.composeshinobicima.features.profile.domain.repo

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.profile.data.model.DeleteSessionRequest

interface ProfileRepo {

    suspend fun deleteSession(body: DeleteSessionRequest): DataState<Boolean>
}