package com.example.composeshinobicima.features.detail.domain.repo

import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem

interface DetailRepo {

    suspend fun getDetailMovie(movieId:Int): DataState<DetailMediaItem>
    suspend fun getDetailTv(seriesId:Int): DataState<DetailMediaItem>
    suspend fun getDetailPerson(personId:Int): DataState<DetailMediaItem>
}