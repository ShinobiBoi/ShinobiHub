package com.example.composeshinobicima.appcore.navigation

import com.example.composeshinobicima.appcore.domain.model.MediaType
import kotlinx.serialization.Serializable

sealed class ScreenResources {

    @Serializable
    object FindScreenRoute : ScreenResources()

    @Serializable
    object HomeScreenRoute : ScreenResources()

    @Serializable
    object ProfileScreenRoute : ScreenResources()

    @Serializable
    data class DiscoverScreenRoute(val genreId: Int) : ScreenResources()

    @Serializable
    data class DetailScreenRoute(
        val id: Int,
        val mediaType: MediaType
    ) : ScreenResources()

    companion object {
        fun fromRoute(route: String): ScreenResources? {
            return when {
                route.contains(HomeScreenRoute::class.qualifiedName ?: "") -> HomeScreenRoute
                route.contains(FindScreenRoute::class.qualifiedName ?: "") -> FindScreenRoute
                route.contains(ProfileScreenRoute::class.qualifiedName ?: "") -> ProfileScreenRoute
                route.contains(
                    DiscoverScreenRoute::class.qualifiedName ?: ""
                ) -> DiscoverScreenRoute(
                    1
                )

                route.contains(DetailScreenRoute::class.qualifiedName ?: "") -> DetailScreenRoute(
                    0,
                    MediaType.Movies
                ) // dummy values

                else -> null
            }
        }
    }

}