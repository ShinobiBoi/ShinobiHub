package com.besha.shinobihub.features.profile.presenation.screen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.ExitToApp
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
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.besha.shinobihub.R
import com.besha.shinobihub.appcore.navigation.ScreenResources
import com.besha.shinobihub.features.profile.presenation.viewmodel.ProfileAction
import com.besha.shinobihub.features.profile.presenation.viewmodel.ProfileViewModel



@Composable
fun ProfileScreen(rootController: NavController, childController: NavController) {

    val context = LocalContext.current
    val profileViewModel = hiltViewModel<ProfileViewModel>()

    val state by profileViewModel.viewStates.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.executeAction(ProfileAction.GetAccount)
        profileViewModel.executeAction(ProfileAction.GetNotifications(context))
    }

    LaunchedEffect(state.loggedOut) {
        if (state.loggedOut.isSuccess){

            rootController.navigate(ScreenResources.AuthScreenRoute) {
                popUpTo(ScreenResources.MainScreeRoute()) { inclusive = true }
                launchSingleTop = true
            }
        }
        else if (state.loggedOut.errorThrowable !=null){

            Toast.makeText(context, state.loggedOut.errorThrowable?.message?:"", Toast.LENGTH_SHORT).show()
        }
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
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Logout",
                    tint = colorResource(R.color.dark_blue),
                    modifier = Modifier.clickable(
                        onClick = {
                            profileViewModel.executeAction(ProfileAction.LogOut)

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

                NotificationPermissionToggle(
                    isEnabled = state.notification,
                    onToggle = {
                        profileViewModel.executeAction(ProfileAction.ToggleNotifications(it, context))
                    }
                )
                HorizontalDivider(color = colorResource(R.color.gray))
                SettingRowSwitch(
                    title = "Dark Mode",
                    checked = isSystemInDarkTheme(),
                    onToggle = {  }
                )
                HorizontalDivider(color = colorResource(R.color.gray))
                SettingRow("Invite a friend") {
                    val shareText = "🎬 Hey! Check this out: https://drive.google.com/drive/folders/1Gj6obw4bI1gS_cUVc4sJ2xKieJ3vGdbi?usp=sharing"

                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }

                    val chooser = Intent.createChooser(intent, "Share link via")
                    context.startActivity(chooser)
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
                    HorizontalDivider(color = colorResource(R.color.gray))
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
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
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


@Composable
fun NotificationPermissionToggle(
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val context = LocalContext.current

    // Launcher to request notification permission
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            // Permission granted, update toggle state
            onToggle(true)
        } else {
            // Permission denied, keep toggle off
            onToggle(false)
        }
    }

    SettingRowSwitch(
        title = "Notification",
        checked = isEnabled,
        onToggle = { checked ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val granted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                if (!granted && checked) {
                    // Request permission
                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    // Already granted or user turned off
                    onToggle(checked)
                }
            } else {
                // Pre-Android 13, permission is automatically granted
                onToggle(checked)
            }
        }
    )
}
