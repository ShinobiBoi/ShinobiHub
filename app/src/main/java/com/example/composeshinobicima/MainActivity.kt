package com.example.composeshinobicima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeshinobicima.appcore.data.remote.ApiServices
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.auth.AuthScreen
import com.example.composeshinobicima.features.home.presentaion.screen.HomeScreen
import com.example.composeshinobicima.features.main.MainScreen
import com.example.composeshinobicima.ui.theme.ComposeShinobiCimaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var apiServices: ApiServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            ComposeShinobiCimaTheme {
                val rootController = rememberNavController()

                    NavHost(
                        navController = rootController,
                        startDestination = ScreenResources.AuthScreenRoute,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        composable<ScreenResources.AuthScreenRoute> {
                            AuthScreen(rootController)

                        }
                        composable<ScreenResources.MainScreeRoute> {
                            MainScreen(rootController)

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

