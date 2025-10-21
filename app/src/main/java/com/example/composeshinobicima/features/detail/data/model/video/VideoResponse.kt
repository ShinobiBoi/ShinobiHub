package com.example.composeshinobicima.features.detail.data.model.video

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    val id: Int? = 0,
    @SerializedName("results")
    val videoItems: List<VideoItem>? = listOf()
)