package com.example.composeshinobicima.appcore.domain.remote

import com.example.composeshinobicima.appcore.data.model.genre.GenreResponse
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse

interface SharedRemoteClient {

    suspend fun getTrendingAll(page: Int): DataState<MediaResponse>

    suspend fun getTrendingMovies(page: Int): DataState<MediaResponse>

    suspend fun getTrendingTv(page: Int): DataState<MediaResponse>

    suspend fun getTrendingPeople(page: Int): DataState<MediaResponse>

    suspend fun getGenreList():DataState<GenreResponse>

}