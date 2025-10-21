package com.example.composeshinobicima.features.detail.data.remote

import android.util.Log
import com.example.composeshinobicima.appcore.data.mappers.toDataState
import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.detail.data.model.credits.CreditsResponse
import com.example.composeshinobicima.features.detail.data.model.detailitem.DetailMediaItemDto
import com.example.composeshinobicima.features.detail.data.model.review.ReviewResponse
import com.example.composeshinobicima.features.detail.data.model.video.VideoResponse
import com.example.composeshinobicima.features.detail.domain.remote.DetailRemoteClient
import javax.inject.Inject

class DetailRemoteClientImp @Inject constructor(val api: ApiServices) :DetailRemoteClient {
    override suspend fun getDetailMovie(movieId: Int): DataState<DetailMediaItemDto> {
       return api.getDetailMovie(movieId).toDataState()

    }

    override suspend fun getDetailTv(seriesId: Int): DataState<DetailMediaItemDto> {
       return api.getDetailTv(seriesId).toDataState()
    }

    override suspend fun getDetailPerson(personId: Int): DataState<DetailMediaItemDto> {
        return api.getDetailPerson(personId).toDataState()
    }

    override suspend fun getMovieVideo(movieId: Int): DataState<VideoResponse> {
        return api.getMovieVideo(movieId).toDataState()
    }

    override suspend fun getTvVideo(seriesId: Int): DataState<VideoResponse> {
        return api.getTvVideo(seriesId).toDataState()
    }

    override suspend fun getMovieCredits(movieId: Int): DataState<CreditsResponse> {
        return api.getMovieCredits(movieId).toDataState()
    }

    override suspend fun getTvCredits(seriesId: Int): DataState<CreditsResponse> {
        return api.getTvCredits(seriesId).toDataState()
    }

    override suspend fun getMovieSimilar(movieId: Int): DataState<MediaResponse> {
        return api.getMovieSimilar(movieId).toDataState()
    }

    override suspend fun getTvSimilar(seriesId: Int): DataState<MediaResponse> {
        return api.getTvSimilar(seriesId).toDataState()
    }

    override suspend fun getMovieReview(movieId: Int): DataState<ReviewResponse> {
        return api.getMovieReviews(movieId).toDataState()
    }

    override suspend fun getTvReview(seriesId: Int): DataState<ReviewResponse> {
       return api.getTvReviews(seriesId).toDataState()
    }


}