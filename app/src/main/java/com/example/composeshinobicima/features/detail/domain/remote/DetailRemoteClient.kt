package com.example.composeshinobicima.features.detail.domain.remote

import com.example.composeshinobicima.appcore.data.model.movie.MediaResponse
import com.example.composeshinobicima.appcore.domain.DataState
import com.example.composeshinobicima.features.detail.data.model.credits.CreditsResponse
import com.example.composeshinobicima.features.detail.data.model.detailitem.DetailMediaItemDto
import com.example.composeshinobicima.features.detail.data.model.review.ReviewResponse
import com.example.composeshinobicima.features.detail.data.model.video.VideoResponse

interface DetailRemoteClient {

    suspend fun getDetailMovie(movieId:Int):DataState<DetailMediaItemDto>
    suspend fun getDetailTv(seriesId:Int):DataState<DetailMediaItemDto>
    suspend fun getDetailPerson(personId:Int):DataState<DetailMediaItemDto>

    suspend fun getMovieVideo(movieId:Int):DataState<VideoResponse>
    suspend fun getTvVideo(seriesId: Int) :DataState<VideoResponse>


    suspend fun getMovieCredits(movieId: Int):DataState<CreditsResponse>
    suspend fun getTvCredits(seriesId: Int):DataState<CreditsResponse>

    suspend fun getMovieSimilar(movieId: Int):DataState<MediaResponse>
    suspend fun getTvSimilar(seriesId: Int):DataState<MediaResponse>

    suspend fun getMovieReview(movieId: Int):DataState<ReviewResponse>
    suspend fun getTvReview(seriesId: Int):DataState<ReviewResponse>




}