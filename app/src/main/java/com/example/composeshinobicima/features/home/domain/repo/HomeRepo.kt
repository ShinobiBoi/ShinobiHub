package com.example.composeshinobicima.features.home.domain.repo

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem

interface HomeRepo {
    suspend fun getPopularMovies(page: Int): DataState<List<MediaItem>>
    suspend fun getTopRatedMovies(page: Int): DataState<List<MediaItem>>
    suspend fun getUpComingMovies(page: Int): DataState<List<MediaItem>>

    suspend fun getOnTheAirTv(page: Int): DataState<List<MediaItem>>
    suspend fun getPopularTv(page: Int): DataState<List<MediaItem>>
    suspend fun getTopRatedTv(page: Int): DataState<List<MediaItem>>



}