package com.example.composeshinobicima.features.find.domain.repo

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem

interface FindRepo {
    suspend fun searchMulti(query:String,page:Int): DataState<List<MediaItem>>
    suspend fun searchMovie(query: String,page: Int): DataState<List<MediaItem>>
    suspend fun searchTv(query: String,page: Int): DataState<List<MediaItem>>
    suspend fun searchPeople(query: String,page: Int): DataState<List<MediaItem>>
}