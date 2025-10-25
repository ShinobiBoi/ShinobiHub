package com.example.composeshinobicima.features.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.composeshinobicima.BottomNavViewModel
import com.example.composeshinobicima.CustomBottomNavigationBar
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.detail.presentaion.screen.MediaDetailScreen
import com.example.composeshinobicima.features.discover.presentaion.screen.DiscoverScreen
import com.example.composeshinobicima.features.find.presenation.screen.FindScreen
import com.example.composeshinobicima.features.home.presentaion.screen.HomeScreen
import com.example.composeshinobicima.features.profile.ProfileScreen


@Composable
fun MainScreen() {



    val navController = rememberNavController()
    val bottomNavViewModel = hiltViewModel<BottomNavViewModel>()
    val currentRoute by bottomNavViewModel.currentRoute.collectAsState()


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {

        if (currentRoute !is ScreenResources.DetailScreenRoute && currentRoute !is ScreenResources.DiscoverScreenRoute){
            CustomBottomNavigationBar(currentRoute) { selectedRoute ->
                if (selectedRoute != currentRoute) {
                    bottomNavViewModel.onRouteSelected(selectedRoute)
                    navController.navigate(selectedRoute) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        restoreState = true
                    }
                }
            }

        }


    }
    ) { innerPadding ->


        NavHost(
            navController = navController,
            startDestination = ScreenResources.HomeScreenRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<ScreenResources.HomeScreenRoute> {
                HomeScreen(controller = navController)

            }
            composable<ScreenResources.FindScreenRoute> {
                FindScreen(navController)
            }

            composable<ScreenResources.DetailScreenRoute> {
                val args = it.toRoute<ScreenResources.DetailScreenRoute>()
                MediaDetailScreen(
                    args.id,
                    args.mediaType,navController
                )
            }
            composable<ScreenResources.DiscoverScreenRoute>{
                val args = it.toRoute<ScreenResources.DiscoverScreenRoute>()
                DiscoverScreen(navController,args.genreId)
            }

            composable<ScreenResources.ProfileScreenRoute> {
                ProfileScreen()
            }
        }

        LaunchedEffect(navController) {
            navController.currentBackStackEntryFlow.collect { backStackEntry ->
                val route = backStackEntry.destination.route
                route?.let {
                    ScreenResources.fromRoute(it)?.let { screen ->
                        bottomNavViewModel.onRouteSelected(screen)
                    }
                }
            }
        }


    }

}