package com.example.composeshinobicima.features.detail.domain.model

import com.example.composeshinobicima.appcore.data.model.genre.Genre
import com.example.composeshinobicima.appcore.data.model.movie.MediaItemDto
import com.example.composeshinobicima.appcore.domain.model.MediaType

data class DetailMediaItem(
    val id: Int,
    val media_type: MediaType, // "movie", "tv", or "person"
    val resolvedTilte:String,
    val resolvedPoster:String,
    val resolvedDate:String,



    val adult: Boolean? = null,
    val popularity: Double? = null,
    val overview: String? = null,
    val genres: List<Genre>? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,

    // Movie-specific
    val title: String? = null,
    val original_title: String? = null,
    val release_date: String? = null,
    val runtime:String?=null,

    // TV-specific
    val name: String? = null,
    val original_name: String? = null,
    val first_air_date: String? = null,
    val origin_country: List<String>? = null,
    val number_of_seasons: Int?= null,

    // Person-specific
    val known_for_department: String? = null,
    val profile_path: String? = null,
    val gender: Int? = null,
    val known_for: List<MediaItemDto>? = null // 👈 recursive list
)