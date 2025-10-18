package com.example.composeshinobicima.features.find.data.repo

import com.example.composeshinobicima.appcore.data.mappers.validate
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.features.find.domain.remote.FindRemoteClient
import com.example.composeshinobicima.features.find.domain.repo.FindRepo
import javax.inject.Inject

class FindRepoImp @Inject constructor(val remote: FindRemoteClient) :FindRepo {
    override suspend fun searchMulti(query: String, page: Int): DataState<List<MediaItem>> {
        return remote.searchMulti(query,page).validate()
    }

    override suspend fun searchMovie(query: String, page: Int): DataState<List<MediaItem>> {
        return remote.searchMovie(query,page).validate()
    }

    override suspend fun searchTv(query: String, page: Int): DataState<List<MediaItem>> {
        return remote.searchTv(query,page).validate()
    }

    override suspend fun searchPeople(query: String, page: Int): DataState<List<MediaItem>> {
        return remote.searchPeople(query,page).validate()
    }
}