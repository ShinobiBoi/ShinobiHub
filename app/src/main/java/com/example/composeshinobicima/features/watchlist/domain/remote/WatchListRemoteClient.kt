package com.example.composeshinobicima.features.watchlist.domain.remote

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.DataState

interface WatchListRemoteClient {
    suspend fun getMovieWatchlist(accountId: Int, sessionId: String): DataState<MediaResponse>
    suspend fun getTvWatchlist(accountId: Int, sessionId: String): DataState<MediaResponse>

}