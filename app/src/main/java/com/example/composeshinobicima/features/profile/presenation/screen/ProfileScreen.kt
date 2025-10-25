package com.example.composeshinobicima.features.profile.presenation.screen

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composeshinobicima.R
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import com.example.composeshinobicima.features.profile.presenation.viewmodel.ProfileAction
import com.example.composeshinobicima.features.profile.presenation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(rootController: NavController, childController: NavController) {

    val context = LocalContext.current
    val profileViewModel = hiltViewModel<ProfileViewModel>()

    val state by profileViewModel.viewStates.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.executeAction(ProfileAction.GetAccount)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Account Section
        Text(
            text = "Account",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(R.color.dark_blue)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_gray)),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val profilePic = state.account.data?.avatar?.tmdb?.avatar_path

                    if (profilePic != null)
                        AsyncImage(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(54.dp),
                            model = "https://image.tmdb.org/t/p/original${profilePic}",
                            contentDescription = "profile pic",
                            contentScale = ContentScale.Crop,
                        )
                    else (
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(colorResource(R.color.dark_blue)),
                                contentAlignment = Alignment.Center
                            ) {
                                val initials =
                                    state.account.data?.username?.take(1)?.uppercase() ?: "?"
                                Text(
                                    text = initials,
                                    color = colorResource(R.color.white),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                            )

                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Welcome,",
                            color = colorResource(R.color.gray),
                            fontSize = 14.sp
                        )
                        Text(
                            text = state.account.data?.username ?: "Guest",
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.dark_blue),
                            fontSize = 16.sp
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = colorResource(R.color.dark_blue),
                    modifier = Modifier.clickable(
                        onClick = {
                            profileViewModel.executeAction(ProfileAction.LogOut)
                            rootController.navigate(ScreenResources.AuthScreenRoute) {
                                popUpTo(ScreenResources.MainScreeRoute) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                )
            }
        }

        // General Section
        Text(
            text = "General",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(R.color.dark_blue)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_gray)),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {


            Column {
                SettingRowSwitch(
                    title = "Dark Mode",
                    checked = isSystemInDarkTheme(),
                    onToggle = {  }
                )
                Divider(color = colorResource(R.color.gray))
                SettingRow("Invite a friend"){

   /*                 val docUri = Uri.parse("https://docs.google.com/document/d/your-doc-id-here/view")

                    val intent = Intent(Intent.ACTION_VIEW, docUri)
                    context.startActivity(intent)*/

                }
            }
        }


        // List Section
        if (state.account.data!=null ) {
            Text(
                text = "List",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colorResource(R.color.dark_blue)
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_gray)),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    SettingRow("Watchlist"){
                        childController.navigate(ScreenResources.WatchListScreenRoute)
                    }
                    Divider(color = colorResource(R.color.gray))
                    SettingRow("Favorites"){
                        childController.navigate(ScreenResources.FavouritesScreenRoute)
                    }
                }
            }
        }


    }
}


@Composable
fun SettingRow(title: String, value: String? = null, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, color = colorResource(R.color.dark_blue), fontSize = 15.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(
                    text = value,
                    color = colorResource(R.color.gray),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = colorResource(R.color.gray),
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
fun SettingRowSwitch(title: String, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, color = colorResource(R.color.dark_blue), fontSize = 15.sp)
        Switch(checked = checked, onCheckedChange = onToggle)
    }
}
