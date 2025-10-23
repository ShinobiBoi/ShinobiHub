package com.example.composeshinobicima.features.discover.data.repo

import com.example.composeshinobicima.appcore.data.mappers.validate
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.features.discover.domain.remote.DiscoverRemoteClient
import com.example.composeshinobicima.features.discover.domain.repo.DiscoverRepo
import javax.inject.Inject

class DiscoverRepoImp @Inject constructor(val remote: DiscoverRemoteClient) : DiscoverRepo {
    override suspend fun getMovieDiscover(genreId: String): DataState<List<MediaItem>> {
        return remote.getMovieDiscover(genreId).validate()
    }

    override suspend fun getTvDiscover(genreId: String): DataState<List<MediaItem>> {
        return remote.getTvDiscover(genreId).validate()
    }
}