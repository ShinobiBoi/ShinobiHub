package com.example.composeshinobicima.features.home.data.remote


import com.example.composeshinobicima.appcore.data.mappers.toDataState
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.home.data.model.account.AccountResponse
import com.example.composeshinobicima.features.home.domain.remote.HomeRemoteClient
import retrofit2.HttpException
import javax.inject.Inject

class HomeRemoteClientImp @Inject constructor(val api: ApiServices) : HomeRemoteClient {


    override suspend fun getPopularMovies(page: Int): DataState<MediaResponse> {
        return try {
            api.getPopularMovies(page).toDataState()


        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }


    override suspend fun getTopRatedMovies(page: Int): DataState<MediaResponse> {
        return try {
            api.getTopRatedMovies(page).toDataState()


        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getUpComingMovies(page: Int): DataState<MediaResponse> {
        return try {
            api.getUpComingMovies(page).toDataState()


        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getOnTheAirTv(page: Int): DataState<MediaResponse> {
        return try {
            api.getOnTheAirTv(page).toDataState()


        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getPopularTv(page: Int): DataState<MediaResponse> {
        return try {
            api.getPopularTv(page).toDataState()


        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getTopRatedTv(page: Int): DataState<MediaResponse> {
        return try {
            api.getTopRatedTv(page).toDataState()


        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getAccount(sessionId: String): DataState<AccountResponse> {
        return try {
            val result = api.getAccount(sessionId)

            if (result.isSuccessful){
                if (result.body()!=null)
                    DataState.Success(result.body()!!)
                else
                    DataState.Empty

            }else{
                DataState.Error(HttpException(result))
            }
        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }


}