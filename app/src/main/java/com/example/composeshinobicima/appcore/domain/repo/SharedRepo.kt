package com.example.composeshinobicima.appcore.domain.repo

import com.example.composeshinobicima.appcore.data.model.genre.Genre
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem

interface SharedRepo {

    suspend fun getTrendingAll(page: Int): DataState<List<MediaItem>>

    suspend fun getTrendingMovies(page: Int): DataState<List<MediaItem>>

    suspend fun getTrendingTv(page: Int): DataState<List<MediaItem>>

    suspend fun getTrendingPeople(page: Int): DataState<List<MediaItem>>

    suspend fun getGenreList():DataState<List<Genre>>
}