package com.example.composeshinobicima.appcore.data.model.movie

import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.domain.model.MediaType

data class MediaItemDto(
    val id: Int,
    val media_type: String? = null,
    val adult: Boolean? = null,
    val popularity: Double? = null,
    val overview: String? = null,
    val genre_ids: List<Int>? = null,
    val poster_path: String? = null,
    val backdrop_path: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,

    // Movie-specific
    val title: String? = null,
    val original_title: String? = null,
    val release_date: String? = null,

    // TV-specific
    val name: String? = null,
    val original_name: String? = null,
    val first_air_date: String? = null,
    val origin_country: List<String>? = null,

    // Person-specific
    val known_for_department: String? = null,
    val profile_path: String? = null,
    val gender: Int? = null,
    val known_for: List<MediaItemDto>? = null // 👈 recursive list
)


fun MediaItemDto.toDomain(): MediaItem {
    return MediaItem(
        id = id,
        media_type = when (media_type) {
            "movie" -> MediaType.Movies
            "tv" -> MediaType.Tv
            "person" -> MediaType.People
            else -> MediaType.All
        },
        adult = adult,
        popularity = popularity,
        overview = overview,
        genre_ids = genre_ids,
        poster_path = poster_path,
        backdrop_path = backdrop_path,
        vote_average = vote_average,
        vote_count = vote_count,
        title = title,
        original_title = original_title,
        release_date = release_date,
        name = name,
        original_name = original_name,
        first_air_date = first_air_date,
        origin_country = origin_country,
        known_for_department = known_for_department,
        profile_path = profile_path,
        gender = gender,
        known_for = known_for?.map { it } // or `.map { it.toDomain() }` if you want to map recursively
    )
}