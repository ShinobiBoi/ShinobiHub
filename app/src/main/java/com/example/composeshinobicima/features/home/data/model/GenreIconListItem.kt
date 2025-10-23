package com.example.composeshinobicima.features.home.data.model

import com.example.composeshinobicima.appcore.data.model.genre.Genre

data class GenreIconListItem(
    val id: Int,
    val title: String,
    val icon: Int,
)


fun GenreIconListItem.toGenre() :Genre{
    return Genre(
        id = id,
        name = title
    )
}