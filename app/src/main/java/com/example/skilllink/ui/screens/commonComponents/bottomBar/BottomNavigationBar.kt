package com.example.skilllink.ui.screens.commonComponents.bottomBar

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.skilllink.ui.theme.CustomFields


@Composable
fun BottomNavigationBar(
    customFields: CustomFields,
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        bottomNavItemList.forEachIndexed { _, bottomNavItem ->
            val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(bottomNavItem.route::class) } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(bottomNavItem.route) {
                         /* popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        } */
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(
                        text = bottomNavItem.title,
                        color = if(isSelected) customFields.primaryTextColor else customFields.secondaryTextColor,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                },
                alwaysShowLabel = false,
                icon = {
                    BadgedBox(
                        badge = {
                            if(bottomNavItem.badgeCount != null) {
                                Badge { Text(text = bottomNavItem.badgeCount.toString()) }
                            } else if (bottomNavItem.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        if(bottomNavItem.selectedIcon != null) {
                            when {
                                isSelected -> { bottomNavItem.selectedIcon }
                                else -> { bottomNavItem.unselectedIcon }
                            }?.let {
                                Icon(
                                    imageVector = it,
                                    contentDescription = bottomNavItem.title,
                                    tint = if(isSelected) customFields.primaryFocusedColor else customFields.primaryUnfocusedColor
                                )
                            }
                        } else if(bottomNavItem.selectedIconDrawable != null) {
                            when {
                                isSelected -> { bottomNavItem.selectedIconDrawable }
                                else -> { bottomNavItem.unselectedIconDrawable }
                            }?.let {
                                Icon(
                                    painter = painterResource(it),
                                    contentDescription = bottomNavItem.title,
                                    tint = if(isSelected) customFields.primaryFocusedColor else customFields.primaryUnfocusedColor
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}