package com.example.composeshinobicima.features.detail.data.repo

import com.example.composeshinobicima.appcore.data.mappers.validate
import com.example.composeshinobicima.appcore.data.model.movie.toDomain
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.detail.data.model.detailitem.DetailMediaItemDto
import com.example.composeshinobicima.features.detail.data.model.detailitem.toDomain
import com.example.composeshinobicima.features.detail.domain.model.DetailMediaItem
import com.example.composeshinobicima.features.detail.domain.remote.DetailRemoteClient
import com.example.composeshinobicima.features.detail.domain.repo.DetailRepo
import javax.inject.Inject

class DetailRepoImp @Inject constructor(val remote: DetailRemoteClient) : DetailRepo {
    override suspend fun getDetailMovie(movieId: Int): DataState<DetailMediaItem> {

        return resultValidate(remote.getDetailMovie(movieId))

    }

    override suspend fun getDetailTv(seriesId: Int): DataState<DetailMediaItem> {
        return resultValidate(remote.getDetailTv(seriesId))
    }

    override suspend fun getDetailPerson(personId: Int): DataState<DetailMediaItem> {
        return resultValidate(remote.getDetailPerson(personId))
    }



    private fun resultValidate(result: DataState<DetailMediaItemDto>): DataState<DetailMediaItem> {
        return when(result){
            is DataState.Success->{
                DataState.Success(result.data.toDomain())
            }
            is DataState.Error->{
                DataState.Error(result.throwable)
            }
            else->{
                DataState.Error(UnknownError())
            }
        }
    }

}