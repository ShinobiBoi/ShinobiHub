package com.example.composeshinobicima.features.login.presentaion.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.login.data.model.login.LoginRequest
import com.example.composeshinobicima.features.login.data.model.session.SessionRequest
import com.example.composeshinobicima.features.login.presentaion.viewmodel.LoginActions
import com.example.composeshinobicima.features.login.presentaion.viewmodel.LoginViewModel

@Composable
fun LoginScreen(rootController: NavController) {


    val loginViewModel = hiltViewModel<LoginViewModel>()
    val state by loginViewModel.viewStates.collectAsState()

    LaunchedEffect(Unit) {
        loginViewModel.executeAction(LoginActions.GetRequestToken)
        loginViewModel.executeAction(LoginActions.Logout)
    }

    LaunchedEffect(
          state.isLoginSuccess
    ) {
        if (state.isLoginSuccess.data == true ) {
            loginViewModel.executeAction(LoginActions.CreateSession(
                SessionRequest(
                    state.requestToken.data!!
                )
            ))
        }
    }

    LaunchedEffect(state.sessionId.data) {
        if (state.sessionId.data!= null){
            loginViewModel.executeAction(LoginActions.SaveSessionId(state.sessionId.data!!))
            rootController.navigate(ScreenResources.MainScreeRoute)
        }
    }


    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize()
        )

        // Centered Card
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xCC1E1E1E)),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        val loginRequest = LoginRequest(
                            username = username,
                            password = password,
                            request_token = state.requestToken.data ?: ""
                        )
                        loginViewModel.executeAction(LoginActions.Login(loginRequest))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("Login", color = Color.Black)
                }

                TextButton(
                    onClick = {
                        rootController.navigate(ScreenResources.MainScreeRoute)
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Continue as Guest", color = Color.LightGray)
                }
            }
        }
    }
}
