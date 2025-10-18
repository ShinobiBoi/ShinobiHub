package com.example.composeshinobicima.features.home.data.repo

import com.example.composeshinobicima.appcore.data.mappers.validate
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.features.home.domain.remote.HomeRemoteClient
import com.example.composeshinobicima.features.home.domain.repo.HomeRepo
import javax.inject.Inject

class HomeRepoImp @Inject constructor(val remote: HomeRemoteClient): HomeRepo {
    override suspend fun getPopularMovies(page: Int): DataState<List<MediaItem>> {
        return remote.getPopularMovies(page).validate()
    }


    override suspend fun getTopRatedMovies(page: Int): DataState<List<MediaItem>> {
        return remote.getTopRatedMovies(page).validate()

    }

    override suspend fun getUpComingMovies(page: Int): DataState<List<MediaItem>> {
        return remote.getUpComingMovies(page).validate()

    }

    override suspend fun getOnTheAirTv(page: Int): DataState<List<MediaItem>> {
        return remote.getOnTheAirTv(page).validate()

    }

    override suspend fun getPopularTv(page: Int): DataState<List<MediaItem>> {
        return remote.getPopularTv(page).validate()

    }

    override suspend fun getTopRatedTv(page: Int): DataState<List<MediaItem>> {
        return remote.getTopRatedTv(page).validate()
    }


}