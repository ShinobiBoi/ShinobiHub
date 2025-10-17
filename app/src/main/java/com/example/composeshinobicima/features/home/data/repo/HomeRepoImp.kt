package com.example.composeshinobicima.features.home.data.repo

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.model.movie.toDomain
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.features.home.domain.remote.HomeRemoteClient
import com.example.composeshinobicima.features.home.domain.repo.HomeRepo
import javax.inject.Inject

class HomeRepoImp @Inject constructor(val remote: HomeRemoteClient): HomeRepo {
    override suspend fun getPopularMovies(page: Int): DataState<List<MediaItem>> {
        return validResponse(remote.getPopularMovies(page))
    }


    override suspend fun getTopRatedMovies(page: Int): DataState<List<MediaItem>> {
        return validResponse(remote.getTopRatedMovies(page))

    }

    override suspend fun getUpComingMovies(page: Int): DataState<List<MediaItem>> {
        return validResponse(remote.getUpComingMovies(page))

    }

    override suspend fun getOnTheAirTv(page: Int): DataState<List<MediaItem>> {
        return validResponse(remote.getOnTheAirTv(page))

    }

    override suspend fun getPopularTv(page: Int): DataState<List<MediaItem>> {
        return validResponse(remote.getPopularTv(page))

    }

    override suspend fun getTopRatedTv(page: Int): DataState<List<MediaItem>> {
        return validResponse(remote.getTopRatedTv(page))
    }

    private fun validResponse(dataState: DataState<MediaResponse>): DataState<List<MediaItem>> {
        return when(dataState){
            is DataState.Success->{
                if (dataState.data.mediaItems.isNullOrEmpty()) DataState.Empty
                else DataState.Success(dataState.data.mediaItems.map { it.toDomain() })
            }
            is DataState.Error->{
                DataState.Error(dataState.throwable)
            }
            else->{
                DataState.Error(UnknownError())
            }
        }
    }
}