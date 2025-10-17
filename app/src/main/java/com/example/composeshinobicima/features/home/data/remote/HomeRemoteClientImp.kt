package com.example.composeshinobicima.features.home.data.remote


import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.home.domain.remote.HomeRemoteClient
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class HomeRemoteClientImp @Inject constructor(val api: ApiServices) : HomeRemoteClient {


    override suspend fun getPopularMovies(page: Int): DataState<MediaResponse> {
        return try {
            val response = api.getPopularMovies(page)
            validResponse(response)

        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }


    override suspend fun getTopRatedMovies(page: Int): DataState<MediaResponse> {
        return try {
            val response = api.getTopRatedMovies(page)
            validResponse(response)

        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getUpComingMovies(page: Int): DataState<MediaResponse> {
        return try {
            val response = api.getUpComingMovies(page)
            validResponse(response)

        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getOnTheAirTv(page: Int): DataState<MediaResponse> {
        return try {
            val response = api.getOnTheAirTv(page)
            validResponse(response)

        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getPopularTv(page: Int): DataState<MediaResponse> {
        return try {
            val response = api.getPopularTv(page)
            validResponse(response)

        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getTopRatedTv(page: Int): DataState<MediaResponse> {
        return try {
            val response = api.getTopRatedTv(page)
            validResponse(response)

        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }


    private fun validResponse(response: Response<MediaResponse>): DataState<MediaResponse> {
        return if (response.isSuccessful) {
            val body = response.body()
            when {
                body == null -> DataState.Error(Throwable("Response body is null"))
                else -> DataState.Success(body)
            }
        } else {
            DataState.Error(HttpException(response))
        }
    }

}