package com.example.composeshinobicima.features.find.data.remote

import com.example.composeshinobicima.appcore.data.mappers.toDataState
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.find.domain.remote.FindRemoteClient
import javax.inject.Inject

class FindRemoteClientImp @Inject constructor(val api: ApiServices) :FindRemoteClient{
    override suspend fun searchMulti(query: String, page: Int): DataState<MediaResponse> {
        return try {
           api.searchMulti(query,page).toDataState()
        }catch (t:Throwable){
            DataState.Error(t)
        }
    }

    override suspend fun searchMovie(query: String, page: Int): DataState<MediaResponse> {
        return try {
            api.searchMovie(query,page).toDataState()
        }catch (t:Throwable){
            DataState.Error(t)
        }
    }

    override suspend fun searchTv(query: String, page: Int): DataState<MediaResponse> {
        return try {
            api.searchTv(query,page).toDataState()
        }catch (t:Throwable){
            DataState.Error(t)
        }
    }

    override suspend fun searchPeople(query: String, page: Int): DataState<MediaResponse> {
        return try {
            api.searchPeople(query,page).toDataState()
        }catch (t:Throwable){
            DataState.Error(t)
        }
    }

}