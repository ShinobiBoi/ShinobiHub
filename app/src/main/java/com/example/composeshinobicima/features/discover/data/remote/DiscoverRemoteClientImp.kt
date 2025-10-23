package com.example.composeshinobicima.features.discover.data.remote

import android.util.Log
import com.example.composeshinobicima.appcore.data.mappers.toDataState
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.discover.domain.remote.DiscoverRemoteClient
import javax.inject.Inject

class DiscoverRemoteClientImp @Inject constructor(val api: ApiServices) : DiscoverRemoteClient {
    override suspend fun getMovieDiscover(genreId: String): DataState<MediaResponse> {
        return api.getMovieDiscover(genreId).toDataState()
    }

    override suspend fun getTvDiscover(genreId: String): DataState<MediaResponse> {
        return api.getTvDiscover(genreId).toDataState()
    }
}