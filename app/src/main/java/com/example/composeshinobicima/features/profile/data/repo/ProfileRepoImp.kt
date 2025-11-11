package com.example.composeshinobicima.features.profile.data.repo

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.profile.data.model.DeleteSessionRequest
import com.example.composeshinobicima.features.profile.domain.remote.ProfileRemoteClient
import com.example.composeshinobicima.features.profile.domain.repo.ProfileRepo
import javax.inject.Inject

class ProfileRepoImp @Inject constructor(private val remote: ProfileRemoteClient) :ProfileRepo {
    override suspend fun deleteSession(body: DeleteSessionRequest): DataState<Boolean> {

        return  when(val result=remote.deleteSession(body)){
            is DataState.Success->{
                DataState.Success(result.data.success)
            }
            is DataState.Error->{
                DataState.Error(result.throwable)
            }
            else ->{
                DataState.Error(UnknownError())
            }
        }
    }
}