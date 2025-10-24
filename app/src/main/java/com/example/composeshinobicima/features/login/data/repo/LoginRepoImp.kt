package com.example.composeshinobicima.features.login.data.repo

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.login.data.model.login.LoginRequest
import com.example.composeshinobicima.features.login.data.model.session.SessionRequest
import com.example.composeshinobicima.features.login.domain.remote.LoginRemoteClient
import com.example.composeshinobicima.features.login.domain.repo.LoginRepo
import javax.inject.Inject

class LoginRepoImp @Inject constructor(val remote: LoginRemoteClient): LoginRepo {
    override suspend fun login(loginRequest: LoginRequest): DataState<Boolean> {
       return when(val result=remote.login(loginRequest)){
            is DataState.Success->{
                DataState.Success(data = result.data.success)

            }
            is DataState.Empty->{
                DataState.Empty

            }
            is DataState.Error->{
                DataState.Error(throwable = result.throwable)
            }

            else->{
                DataState.Error(UnknownError())
            }
        }
    }

    override suspend fun createToken(): DataState<String> {
        return when(val result=remote.createToken()){
            is DataState.Success->{
                DataState.Success(data = result.data.request_token)
            }
            is DataState.Empty->{
                DataState.Empty
            }
            is DataState.Error->{
                DataState.Error(throwable = result.throwable)
            }

            else->{
                DataState.Error(UnknownError())
            }
        }
    }

    override suspend fun createSession(sessionRequest: SessionRequest): DataState<String> {
        return when(val result=remote.createSession(sessionRequest)){
            is DataState.Success->{
                DataState.Success(data = result.data.session_id)

            }
            is DataState.Empty->{
                DataState.Empty

            }
            is DataState.Error->{
                DataState.Error(throwable = result.throwable)
            }

            else->{
                DataState.Error(UnknownError())
            }
        }
    }
}