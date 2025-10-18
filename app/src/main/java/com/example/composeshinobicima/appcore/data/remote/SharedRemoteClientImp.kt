package com.example.composeshinobicima.appcore.data.remote

import com.example.composeshinobicima.appcore.data.mappers.toDataState
import com.example.composeshinobicima.appcore.data.model.genre.GenreResponse
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.remote.SharedRemoteClient
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class SharedRemoteClientImp @Inject constructor(val api: ApiServices) : SharedRemoteClient {


    override suspend fun getTrendingAll(page: Int): DataState<MediaResponse> {
        return try {
            api.getTrendingAll(page).toDataState()

        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getTrendingMovies(page: Int): DataState<MediaResponse> {
        return try {
            api.getTrendingMovies(page).toDataState()


        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }


    override suspend fun getTrendingTv(page: Int): DataState<MediaResponse> {
        return try {
            api.getTrendingTv(page).toDataState()


        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getTrendingPeople(page: Int): DataState<MediaResponse> {
        return try {
            api.getTrendingPeople(page).toDataState()

        } catch (t: Throwable) {
            DataState.Error(t)
        }
    }

    override suspend fun getGenreList(): DataState<GenreResponse> {
        return try {
            val response = api.getGenreList()

            if (response.isSuccessful) {
                val body = response.body()

                when {
                    body == null -> DataState.Error(Throwable("Response body is null"))
                    else -> DataState.Success(body)
                }
            } else {
                DataState.Error(HttpException(response))
            }

        } catch (t: Throwable) {
            DataState.Error(t)
        }

    }


}