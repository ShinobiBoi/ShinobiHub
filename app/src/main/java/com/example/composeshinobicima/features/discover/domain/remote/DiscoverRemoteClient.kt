package com.example.composeshinobicima.features.discover.domain.remote

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.DataState

interface DiscoverRemoteClient {
    suspend fun getMovieDiscover(genreId:String):DataState<MediaResponse>
    suspend fun getTvDiscover(genreId:String):DataState<MediaResponse>

}