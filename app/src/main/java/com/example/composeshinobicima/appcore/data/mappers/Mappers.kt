package com.example.composeshinobicima.appcore.data.mappers

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.model.movie.toDomain
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.toDataState(): DataState<T> {
    return if (isSuccessful) {
        val body = body()
        if (body != null) {
            DataState.Success(body)
        } else {
            DataState.Error(Throwable("Response body is null"))
        }
    } else {
        DataState.Error(HttpException(this))
    }
}

fun DataState<MediaResponse>.validate(): DataState<List<MediaItem>> {
    return when(this){
        is DataState.Success->{
            if (this.data.mediaItems.isNullOrEmpty()) DataState.Empty
            else DataState.Success(this.data.mediaItems.map { it.toDomain() })
        }
        is DataState.Error->{
            DataState.Error(this.throwable)
        }
        else->{
            DataState.Error(UnknownError())
        }
    }
}
