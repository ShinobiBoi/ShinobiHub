package com.example.composeshinobicima.appcore.data.model.movie

import com.google.gson.annotations.SerializedName

data class MediaResponse(
    val page: Int? = 0,
    @SerializedName("results")
    val mediaItems: List<MediaItemDto>? = listOf(),
    val total_pages: Int? = 0,
    val total_results: Int? = 0
)