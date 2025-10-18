package com.example.composeshinobicima.features.find.domain.remote

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.DataState

interface FindRemoteClient {

    suspend fun searchMulti(query:String,page:Int):DataState<MediaResponse>
    suspend fun searchMovie(query: String,page: Int):DataState<MediaResponse>
    suspend fun searchTv(query: String,page: Int):DataState<MediaResponse>
    suspend fun searchPeople(query: String,page: Int):DataState<MediaResponse>

}