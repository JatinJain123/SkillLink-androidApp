package com.example.skilllink.ui.screens.learnerScreens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.skilllink.ui.navigation.bottomNavItemList
import com.example.skilllink.ui.theme.CustomFields


@Composable
fun BottomNavigationBar(
    customFields: CustomFields,
    navController: NavController
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItemList.forEachIndexed { index, bottomNavItem ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            bottomNavItem.navigation(navController)
                        },
                        label = {
                            Text(
                                text = bottomNavItem.title,
                                color = if(selectedItemIndex == index) customFields.primaryTextColor else customFields.secondaryTextColor,
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
                                        selectedItemIndex == index -> { bottomNavItem.selectedIcon }
                                        else -> { bottomNavItem.unselectedIcon }
                                    }?.let {
                                        Icon(
                                            imageVector = it,
                                            contentDescription = bottomNavItem.title,
                                            tint = if(selectedItemIndex == index) customFields.primaryFocusedColor else customFields.primaryUnfocusedColor
                                        )
                                    }
                                } else if(bottomNavItem.selectedIconDrawable != null) {
                                    when {
                                        selectedItemIndex == index -> { bottomNavItem.selectedIconDrawable }
                                        else -> { bottomNavItem.unselectedIconDrawable }
                                    }?.let {
                                        Icon(
                                            painter = painterResource(it),
                                            contentDescription = bottomNavItem.title,
                                            tint = if(selectedItemIndex == index) customFields.primaryFocusedColor else customFields.primaryUnfocusedColor
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) { }
    }
}