package com.example.composeshinobicima.features.favourite.domain.remote

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.DataState

interface FavouriteRemoteClient {
    suspend fun getMovieFavourite(accountId: Int, sessionId: String): DataState<MediaResponse>
    suspend fun getTvFavourite(accountId: Int, sessionId: String): DataState<MediaResponse>

}