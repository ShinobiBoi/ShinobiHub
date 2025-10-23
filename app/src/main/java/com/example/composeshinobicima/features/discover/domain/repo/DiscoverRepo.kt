package com.example.composeshinobicima.features.discover.domain.repo

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem

interface DiscoverRepo {

    suspend fun getMovieDiscover(genreId:String): DataState<List<MediaItem>>
    suspend fun getTvDiscover(genreId:String): DataState<List<MediaItem>>
}