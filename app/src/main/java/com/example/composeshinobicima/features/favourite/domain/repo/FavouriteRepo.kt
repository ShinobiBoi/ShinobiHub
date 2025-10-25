package com.example.composeshinobicima.features.favourite.domain.repo

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem

interface FavouriteRepo {
    suspend fun getMovieFavourite(accountId: Int, sessionId: String): DataState<List<MediaItem>>
    suspend fun getTvFavourite(accountId: Int, sessionId: String): DataState<List<MediaItem>>

}