package com.example.composeshinobicima

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    val selectedIndicatorColor = Color(0xFF001F3F)
    val unselectedIconColor = Color(0xFF72747C)
    val selectedIconColorOnCircle = Color.White
    val labelColor = Color(0xFF72747C)

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

                val iconTint by animateColorAsState(
                    targetValue = if (isSelected) labelColor else unselectedIconColor,
                    label = "Icon Color Animation"
                )

                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onItemSelected(item.route) },
                    icon = {
                        val iconContent = @Composable {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label,
                                tint = if (isSelected) selectedIconColorOnCircle else iconTint
                            )
                        }

                        if (isSelected) {
                            Surface(
                                shape = CircleShape,
                                color = selectedIndicatorColor,
                                modifier = Modifier.size(60.dp)
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
                            color = if (isSelected) labelColor else unselectedIconColor
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = Color.Transparent,
                        unselectedIconColor = unselectedIconColor,
                        selectedTextColor = labelColor,
                        unselectedTextColor = unselectedIconColor
                    )
                )
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun PreviewBottomNav_HomeSelected() {
    MaterialTheme {
        Surface {
            // Preview with 'home' selected
            CustomBottomNavigationBar(selectedRoute = ScreenResources.HomeScreenRoute)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNav_FeedSelected() {
    MaterialTheme {
        Surface {
            // Preview with 'feed' selected (a non-circular item)
            CustomBottomNavigationBar(selectedRoute = ScreenResources.FindScreenRoute)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNav_ProfileSelected() {
    MaterialTheme {
        Surface {
            // Preview with 'profile' selected (a non-circular item)
            CustomBottomNavigationBar(selectedRoute = ScreenResources.ProfileScreenRoute)
        }
    }
}*/
