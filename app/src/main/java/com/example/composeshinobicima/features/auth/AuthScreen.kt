package com.example.composeshinobicima.features.auth

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.login.presentaion.screen.LoginScreen
import com.example.composeshinobicima.features.splash.presentation.screen.SplashScreen


@Composable
fun AuthScreen(rootController: NavController) {

    val childController = rememberNavController()
    NavHost(
        navController = childController,
        startDestination = ScreenResources.SplashScreenRoute,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<ScreenResources.SplashScreenRoute> {
            SplashScreen(rootController, childController)
        }
        composable<ScreenResources.LoginScreeRoute> {
            LoginScreen(rootController)
        }
    }
}
