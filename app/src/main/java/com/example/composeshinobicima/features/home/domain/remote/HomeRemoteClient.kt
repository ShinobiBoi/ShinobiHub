package com.example.composeshinobicima.features.home.domain.remote

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.DataState

interface HomeRemoteClient {

    suspend fun getPopularMovies(page: Int): DataState<MediaResponse>
    suspend fun getTopRatedMovies(page: Int): DataState<MediaResponse>
    suspend fun getUpComingMovies(page: Int): DataState<MediaResponse>

    suspend fun getOnTheAirTv(page: Int): DataState<MediaResponse>
    suspend fun getPopularTv(page: Int): DataState<MediaResponse>
    suspend fun getTopRatedTv(page: Int): DataState<MediaResponse>



}