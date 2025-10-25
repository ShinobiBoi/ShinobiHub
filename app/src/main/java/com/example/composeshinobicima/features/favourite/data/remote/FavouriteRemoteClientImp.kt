package com.example.composeshinobicima.features.favourite.data.remote

import com.example.composeshinobicima.appcore.data.mappers.toDataState
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.favourite.domain.remote.FavouriteRemoteClient
import javax.inject.Inject

class FavouriteRemoteClientImp @Inject constructor(private val api: ApiServices) : FavouriteRemoteClient {
    override suspend fun getMovieFavourite(
        accountId: Int,
        sessionId: String
    ): DataState<MediaResponse> {
        return api.getFavoriteMovies(accountId, sessionId).toDataState()
    }

    override suspend fun getTvFavourite(
        accountId: Int,
        sessionId: String
    ): DataState<MediaResponse> {
       return api.getFavoriteTv(accountId,sessionId).toDataState()
    }

}