package com.example.composeshinobicima.appcore.navigation

import kotlinx.serialization.Serializable

sealed class ScreenResources {

    @Serializable
    object FindScreenRoute : ScreenResources()

    @Serializable
    object HomeScreenRoute : ScreenResources()

    @Serializable
    object ProfileScreenRoute:ScreenResources()


    companion object {
        fun fromRoute(route: String): ScreenResources? {
            return when (route) {
                HomeScreenRoute::class.qualifiedName -> HomeScreenRoute
                FindScreenRoute::class.qualifiedName -> FindScreenRoute
                ProfileScreenRoute::class.qualifiedName -> ProfileScreenRoute
                else -> null
            }
        }
    }

}