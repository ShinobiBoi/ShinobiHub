package com.example.composeshinobicima.features.detail.data.model.mark

data class MarkRequest(
    val media_type: String, // "movie" or "tv"
    val media_id: Int,
    val favorite: Boolean? = null,
    val watchlist: Boolean? = null
)
