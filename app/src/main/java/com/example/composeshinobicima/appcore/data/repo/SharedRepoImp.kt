package com.example.composeshinobicima.appcore.data.repo

import com.example.composeshinobicima.appcore.data.mappers.validate
import com.example.composeshinobicima.appcore.data.model.genre.Genre
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.domain.remote.SharedRemoteClient
import com.example.composeshinobicima.appcore.domain.repo.SharedRepo

import javax.inject.Inject

class SharedRepoImp @Inject constructor(val sharedRemoteClient: SharedRemoteClient) : SharedRepo {
    override suspend fun getTrendingAll(page: Int): DataState<List<MediaItem>> {

        return sharedRemoteClient.getTrendingAll(page).validate()

    }

    override suspend fun getTrendingMovies(page: Int): DataState<List<MediaItem>> {
        return sharedRemoteClient.getTrendingMovies(page).validate()
    }

    override suspend fun getTrendingTv(page: Int): DataState<List<MediaItem>> {
        return sharedRemoteClient.getTrendingTv(page).validate()
    }

    override suspend fun getTrendingPeople(page: Int): DataState<List<MediaItem>> {
        return sharedRemoteClient.getTrendingPeople(page).validate()
    }

    override suspend fun getGenreList(): DataState<List<Genre>> {

        return when (val dataState = sharedRemoteClient.getGenreList()) {
            is DataState.Success -> {
                if (dataState.data.genres.isNullOrEmpty()) DataState.Empty
                else DataState.Success(dataState.data.genres)
            }

            is DataState.Error -> {
                DataState.Error(dataState.throwable)
            }

            else -> {
                DataState.Error(UnknownError())
            }

        }

    }




}