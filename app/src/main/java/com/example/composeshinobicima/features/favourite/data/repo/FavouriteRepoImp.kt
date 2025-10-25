package com.example.composeshinobicima.features.favourite.data.repo

import com.example.composeshinobicima.appcore.data.mappers.validate
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.features.favourite.data.remote.FavouriteRemoteClientImp
import com.example.composeshinobicima.features.favourite.domain.repo.FavouriteRepo
import javax.inject.Inject

class FavouriteRepoImp @Inject constructor(private val remote: FavouriteRemoteClientImp): FavouriteRepo {
    override suspend fun getMovieFavourite(
        accountId: Int,
        sessionId: String
    ): DataState<List<MediaItem>> {
        return remote.getMovieFavourite(accountId, sessionId).validate()
    }

    override suspend fun getTvFavourite(
        accountId: Int,
        sessionId: String
    ): DataState<List<MediaItem>> {
        return remote.getTvFavourite(accountId,sessionId).validate()
    }
}