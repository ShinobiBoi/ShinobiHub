package com.example.composeshinobicima.features.watchlist.data.repo

import com.example.composeshinobicima.appcore.data.mappers.validate
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.features.watchlist.domain.remote.WatchListRemoteClient
import com.example.composeshinobicima.features.watchlist.domain.repo.WatchListRepo
import javax.inject.Inject

class WatchListRepoImp @Inject constructor(val remote: WatchListRemoteClient) :WatchListRepo{
    override suspend fun getMovieWatchlist(
        accountId: Int,
        sessionId: String
    ): DataState<List<MediaItem>> {
        return remote.getMovieWatchlist(accountId,sessionId).validate()
    }

    override suspend fun getTvWatchlist(
        accountId: Int,
        sessionId: String
    ): DataState<List<MediaItem>> {
        return remote.getTvWatchlist(accountId,sessionId).validate()
    }
}