package com.example.composeshinobicima.features.profile

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.composeshinobicima.R

@Composable
fun ProfileScreen() {

    var notificationsEnabled by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
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
            Color.Blue

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
                        Image(
                            painter = rememberAsyncImagePainter("https://i.pravatar.cc/150?img=10"),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = "Welcome,", color = Color.Gray, fontSize = 14.sp)
                            Text(
                                text = "Riju Basu",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0A1F44),
                                fontSize = 16.sp
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout",
                        tint = Color(0xFF0A1F44)
                    )
                }
            }

            // General Section
            Text(
                text = "General",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0A1F44)
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    SettingRowSwitch(
                        title = "Notification",
                        checked = notificationsEnabled,
                        onToggle = { notificationsEnabled = it }
                    )
                    Divider(color = Color.LightGray)
                    SettingRow("Dark Mode", "System")
                    Divider(color = Color.LightGray)
                    SettingRow("Text Size", "Normal")
                    Divider(color = Color.LightGray)
                    SettingRow("Invite a friend")
                }
            }

            // List Section (New)
            Text(
                text = "List",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0A1F44)
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    SettingRow("Watchlist")
                    Divider(color = Color.LightGray)
                    SettingRow("Favorites")
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
        Text(text = title, color = Color(0xFF0A1F44), fontSize = 15.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (value != null) {
                Text(
                    text = value,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.Gray,
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
        Text(text = title, color = Color(0xFF0A1F44), fontSize = 15.sp)
        Switch(checked = checked, onCheckedChange = onToggle)
    }
}
