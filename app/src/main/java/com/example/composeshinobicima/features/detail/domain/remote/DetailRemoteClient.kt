package com.example.composeshinobicima.features.detail.domain.remote

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.detail.data.model.detailitem.DetailMediaItemDto

interface DetailRemoteClient {

    suspend fun getDetailMovie(movieId:Int):DataState<DetailMediaItemDto>
    suspend fun getDetailTv(seriesId:Int):DataState<DetailMediaItemDto>
    suspend fun getDetailPerson(personId:Int):DataState<DetailMediaItemDto>

}