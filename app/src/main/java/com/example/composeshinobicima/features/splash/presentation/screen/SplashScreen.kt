package com.example.composeshinobicima.features.splash.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.splash.presentation.viewmodel.SplashAction
import com.example.composeshinobicima.features.splash.presentation.viewmodel.SplashViewModel
import com.example.composeshinobicima.ui.theme.germaniaOneFamily
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(rootController: NavController, childController: NavController) {


    val splashViewModel= hiltViewModel<SplashViewModel>()
    val state by splashViewModel.viewStates.collectAsState()


    LaunchedEffect(state.sessionId) {
        splashViewModel.executeAction(SplashAction.GetSessionId)
        delay(3000)

        if (state.sessionId.isNullOrEmpty()) {
            childController.navigate(ScreenResources.LoginScreeRoute) {
                popUpTo(ScreenResources.SplashScreenRoute) { inclusive = true }
                launchSingleTop = true
            }
        } else {
            rootController.navigate(ScreenResources.MainScreeRoute) {
                popUpTo(ScreenResources.AuthScreenRoute) { inclusive = true }
                launchSingleTop = true
            }
        }
    }


    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black)
    ) {

        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "ShinobiHub",
                fontFamily = germaniaOneFamily,
                color = Color.White,
                fontSize = 30.sp
            )

            Image(
                painter = painterResource(id = R.drawable.shinobihub),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .border(2.dp, Color.White)
                    .size(40.dp)

            )
        }

    }




}