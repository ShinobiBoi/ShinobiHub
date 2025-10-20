package com.example.composeshinobicima

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeshinobicima.appcore.navigation.ScreenResources

data class BottomNavItem(
    val route: ScreenResources,
    val label: String,
    val icon: Int,
)

val navItems = listOf(
    BottomNavItem(route = ScreenResources.FindScreenRoute, label = "Find", icon = R.drawable.selected_searchicon),
    BottomNavItem(route = ScreenResources.HomeScreenRoute, label = "Home", icon = R.drawable.selected_homeicon),
    BottomNavItem(route = ScreenResources.ProfileScreenRoute, label = "Profile", icon = R.drawable.selected_profileicon),
)

// A simplified version for previewing
@Composable
fun CustomBottomNavigationBar(
    selectedRoute: ScreenResources,
    onItemSelected: (ScreenResources) -> Unit
) {
    val containerColor = Color.White
    val selectedIndicatorColor = colorResource(R.color.dark_blue)
    val selectedIconColorOnCircle = Color.White

    // Wrap the NavigationBar with Surface to round its corners
    Surface(
        color = containerColor,
        border = BorderStroke(2.dp, selectedIndicatorColor),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        shadowElevation = 8.dp, // optional shadow
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NavigationBar(
            containerColor = Color.Transparent, // important! transparent to use Surface color
            tonalElevation = 0.dp
        ) {
            navItems.forEach { item ->
                val isSelected = selectedRoute == item.route



                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onItemSelected(item.route) },
                    icon = {
                        val iconContent = @Composable {
                            Icon(
                                painter = painterResource(id = item.icon),
                                modifier = Modifier.size(28.dp),
                                contentDescription = item.label,
                                tint = if (isSelected) selectedIconColorOnCircle else Color.Gray
                            )
                        }

                        if (isSelected) {
                            Surface(
                                shape = CircleShape,
                                color = selectedIndicatorColor,
                                modifier = Modifier.size(48.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                    iconContent()
                                }
                            }
                        } else {
                            iconContent()
                        }
                    },
                    label = {
                        Text(
                            text = if (isSelected) "" else item.label,
                            color = Color.Gray
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = Color.Transparent,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.Gray,
                        unselectedTextColor =Color.Gray
                    )
                )
            }
        }
    }
}



