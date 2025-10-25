package com.example.composeshinobicima.features.watchlist.data.remote

import com.example.composeshinobicima.appcore.data.mappers.toDataState
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.watchlist.domain.remote.WatchListRemoteClient
import javax.inject.Inject

class WatchListRemoteClientImp @Inject constructor(val api: ApiServices) :WatchListRemoteClient{
    override suspend fun getMovieWatchlist(
        accountId: Int,
        sessionId: String
    ): DataState<MediaResponse> {
        return api.getWatchlistMovies(accountId, sessionId).toDataState()
    }

    override suspend fun getTvWatchlist(
        accountId: Int,
        sessionId: String
    ): DataState<MediaResponse> {
        return api.getWatchlistTv(accountId,sessionId).toDataState()
    }
}