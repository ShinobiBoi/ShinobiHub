package com.example.composeshinobicima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.domain.model.MediaType
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.detail.presentaion.screen.MediaDetailScreen
import com.example.composeshinobicima.features.home.presentaion.screen.HomeScreen
import com.example.composeshinobicima.features.find.presenation.screen.FindScreen
import com.example.composeshinobicima.ui.theme.ComposeShinobiCimaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {


    @Inject
    lateinit var apiServices: ApiServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeShinobiCimaTheme {



                val navController = rememberNavController()
                val bottomNavViewModel = hiltViewModel<BottomNavViewModel>()
                val currentRoute by bottomNavViewModel.currentRoute.collectAsState()


                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {

                    if (currentRoute !is ScreenResources.DetailScreenRoute){
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
                                args.mediaType
                            )
                        }

                        composable<ScreenResources.ProfileScreenRoute> { }
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ComposeShinobiCimaTheme {
        val mockNavController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                CustomBottomNavigationBar(
                    ScreenResources.HomeScreenRoute
                ) {}
            }
        ) { innerPadding ->
            // Mock Home Screen for preview
            val x = innerPadding
            HomeScreen(controller = mockNavController)
        }
    }
}

