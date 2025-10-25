package com.example.composeshinobicima.features.watchlist.domain.repo

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem

interface WatchListRepo {
    suspend fun getMovieWatchlist(accountId: Int, sessionId: String): DataState<List<MediaItem>>
    suspend fun getTvWatchlist(accountId: Int, sessionId: String): DataState<List<MediaItem>>

}