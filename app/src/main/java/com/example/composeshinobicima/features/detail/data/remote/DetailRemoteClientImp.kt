package com.example.composeshinobicima.features.detail.data.remote

import android.util.Log
import com.example.composeshinobicima.appcore.data.mappers.toDataState
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.detail.data.model.detailitem.DetailMediaItemDto
import com.example.composeshinobicima.features.detail.domain.remote.DetailRemoteClient
import javax.inject.Inject

class DetailRemoteClientImp @Inject constructor(val api: ApiServices) :DetailRemoteClient {
    override suspend fun getDetailMovie(movieId: Int): DataState<DetailMediaItemDto> {
       return api.getDetailMovie(movieId).toDataState()
    }

    override suspend fun getDetailTv(seriesId: Int): DataState<DetailMediaItemDto> {
        Log.d("TAGG","${api.getDetailTv(seriesId).body()}")
       return api.getDetailTv(seriesId).toDataState()
    }

    override suspend fun getDetailPerson(personId: Int): DataState<DetailMediaItemDto> {
        return api.getDetailPerson(personId).toDataState()
    }

}