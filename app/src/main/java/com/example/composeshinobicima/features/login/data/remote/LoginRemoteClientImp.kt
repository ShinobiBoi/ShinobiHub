package com.example.composeshinobicima.features.login.data.remote

import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.login.data.model.login.LoginRequest
import com.example.composeshinobicima.features.login.data.model.login.LoginResponse
import com.example.composeshinobicima.features.login.data.model.session.SessionRequest
import com.example.composeshinobicima.features.login.data.model.session.SessionResponse
import com.example.composeshinobicima.features.login.data.model.token.TokenResponse
import com.example.composeshinobicima.features.login.domain.remote.LoginRemoteClient
import retrofit2.HttpException
import javax.inject.Inject

class LoginRemoteClientImp @Inject constructor(val apiServices: ApiServices) : LoginRemoteClient {
    override suspend fun login(loginRequest: LoginRequest): DataState<LoginResponse> {

        return try {
            val result = apiServices.validateWithLogin(loginRequest)
            if (result.isSuccessful) {
                if (result.body() != null)
                    DataState.Success(data = result.body()!!)
                else
                    DataState.Empty
            } else
                DataState.Error(throwable = HttpException(result))
        } catch (t: Throwable) {
            DataState.Error(throwable = t)
        }
    }

    override suspend fun createToken(): DataState<TokenResponse> {
        return try {
            val result = apiServices.createRequestToken()
            if (result.isSuccessful) {
                if (result.body() != null)
                    DataState.Success(data = result.body()!!)
                else
                    DataState.Empty
            } else
                DataState.Error(throwable = HttpException(result))
        } catch (t: Throwable) {
            DataState.Error(throwable = t)
        }
    }


    override suspend fun createSession(sessionRequest: SessionRequest): DataState<SessionResponse> {
        return try {
            val result = apiServices.createSession(sessionRequest)
            if (result.isSuccessful) {
                if (result.body() != null)
                    DataState.Success(data = result.body()!!)
                else
                    DataState.Empty
            } else
                DataState.Error(throwable = HttpException(result))
        } catch (t: Throwable) {
            DataState.Error(throwable = t)
        }
    }
}