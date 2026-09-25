package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import com.example.ui.ScreenRoute

data class NavTabItem(
    val route: ScreenRoute,
    val label: String,
    val icon: ImageVector,
    val testTag: String
)

@Composable
fun LiteracyBottomNavigationBar(
    currentRoute: ScreenRoute,
    onTabSelected: (ScreenRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavTabItem(ScreenRoute.HOME, "Beranda", Icons.Default.Home, "nav_tab_home"),
        NavTabItem(ScreenRoute.STORY_LIST, "Perpustakaan", Icons.Default.MenuBook, "nav_tab_library"),
        NavTabItem(ScreenRoute.GAMES_MENU, "Games", Icons.Default.Games, "nav_tab_games"),
        NavTabItem(ScreenRoute.PROGRESS_DASHBOARD, "Progres", Icons.Default.BarChart, "nav_tab_progress")
    )

    NavigationBar(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("bottom_nav_bar"),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp
                    )
                },
                selected = isSelected,
                onClick = { onTabSelected(item.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.testTag(item.testTag)
            )
        }
    }
}
