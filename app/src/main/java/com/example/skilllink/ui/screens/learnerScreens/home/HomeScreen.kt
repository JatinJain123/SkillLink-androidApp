package com.example.skilllink.ui.screens.learnerScreens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.skilllink.domain.model.local.LocalAppDependencies
import com.example.skilllink.domain.model.local.LocalUiStates
import com.example.skilllink.domain.model.remote.Reel
import com.example.skilllink.ui.reusableComponents.cards.FeedReelCard
import com.example.skilllink.ui.screens.commonComponents.bottomBar.BottomNavigationBar
import com.example.skilllink.ui.screens.commonComponents.exoPlayer.rememberExoPlayerPool
import com.example.skilllink.ui.theme.LocalCustomColors
import com.example.skilllink.utils.testReelData

@Composable
fun HomeScreen(
    navController: NavController
) {
    val appDependencies = LocalAppDependencies.current
    val userPrefsStoreViewModel = appDependencies.userPrefsStoreViewModel
    var userName by remember { mutableStateOf("User") }
    var profilePic by remember { mutableStateOf("") }

    val testReels = testReelData.reels

    userPrefsStoreViewModel?.let {
        val name by it.userName.collectAsState()
        val picUri by it.profilePic.collectAsState()

        LaunchedEffect(name, picUri) {
            userName = name
            profilePic = picUri
        }
    }

    ScreenView(
        userName = userName,
        profilePic = profilePic,
        testReels = testReels,
        navController = navController
    )
}

@Composable
fun ScreenView(
    userName: String,
    profilePic: String,
    testReels: List<Reel>,
    navController: NavController
) {
    // custom fields
    val customFields = LocalCustomColors.current

    // ui states
    val uiStates = LocalUiStates.current
    var mikeOn by rememberSaveable { mutableStateOf(uiStates.mikeOn) }

    // loading exo player instances
    val exoPlayersPool = rememberExoPlayerPool(reels = testReels, mikeOn = mikeOn)

    // creating list state of lazy Column
    val listState = rememberLazyListState()

    // finding the reel which is closed to center
    val focusedIndex by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            val viewportCenter = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2

            val videoItems = visibleItems.filter { item ->
                item.index >= 2 && item.index < (2 + testReels.size)
            }

            if (videoItems.isEmpty()) {
                -1
            } else {
                val closestVideoItem = videoItems.minByOrNull { item ->
                    val itemCenter = item.offset + item.size / 2
                    kotlin.math.abs(itemCenter - viewportCenter)
                }
                closestVideoItem?.let { it.index - 2 } ?: -1
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Header(
                userName = userName,
                profilePic = profilePic,
                customFields = customFields,
                navController = navController
            )
        },
        bottomBar = {
            BottomNavigationBar(
                customFields = customFields,
                navController = navController
            )
        }
    ) { innerPadding ->
        LazyColumn (
            state = listState,
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                FollowersScroll(
                    customFields = customFields,
                    navController = navController
                )
            }

            item {
                TrendingScroll(customFields = customFields)
            }

            if(testReels.isEmpty()) {
                item {
                    Text(
                        text = "No Updates Yet !",
                        color = customFields.secondaryTextColor,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = customFields.midPadding)
                    )
                }
            } else {
                itemsIndexed(testReels) { index, reel ->
                    val exoPlayer = exoPlayersPool.createAndGet(index)
                    FeedReelCard(
                        reel = reel,
                        exoPlayer = exoPlayer,
                        isPlaying = index == focusedIndex,
                        mikeOn = mikeOn,
                        onMikeClick = {
                            mikeOn = !mikeOn
                            uiStates.mikeOn = mikeOn
                        },
                        customFields = customFields
                    )
                }
            }
        }
    }
}