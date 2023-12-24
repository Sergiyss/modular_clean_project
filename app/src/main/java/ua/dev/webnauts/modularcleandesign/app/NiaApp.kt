/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua.dev.webnauts.modularcleandesign.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import ua.dev.webnauts.modularcleandesign.R
import ua.dev.webnauts.modularcleandesign.navigation.NiaNavHost
import ua.dev.webnauts.modularcleandesign.navigation.TopLevelDestination
import ua.dev.webnauts.ui.designsystem.NiaBackground
import ua.dev.webnauts.ui.designsystem.NiaGradientBackground
import ua.dev.webnauts.ui.ui.theme.GradientColors
import ua.dev.webnauts.ui.ui.theme.LocalGradientColors
import ua.dev.webnauts.utils.util.NetworkMonitor

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun NiaApp(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    appState: NiaAppState = rememberNiaAppState(
        networkMonitor = networkMonitor,
        windowSizeClass = windowSizeClass,
    ),
) {

    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    NiaBackground {
        NiaGradientBackground(
            gradientColors = if (true) {
                LocalGradientColors.current
            } else {
                GradientColors()
            },
        ) {
            val snackbarHostState = remember { SnackbarHostState() }

            val isOffline by appState.isOffline.collectAsStateWithLifecycle()

            // If user is not connected to the internet show a snack bar to inform them.
            val notConnectedMessage = stringResource(R.string.not_connected)
            LaunchedEffect(isOffline) {
                if (isOffline) {
                    snackbarHostState.showSnackbar(
                        message = notConnectedMessage,
                        duration = Indefinite,
                    )
                }
            }

            if (showSettingsDialog) {
//                SettingsDialog(
//                    onDismiss = { showSettingsDialog = false },
//                )
            }

           // val unreadDestinations by appState.topLevelDestinationsWithUnreadResources.collectAsStateWithLifecycle()

            Scaffold(
                modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        NiaBottomBar(
                            destinations = appState.topLevelDestinations,
                            onNavigateToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                            modifier = Modifier.testTag("NiaBottomBar"),
                        )
                    }
                },
            ) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        ),
                ) {
                    if (appState.shouldShowNavRail) {
                        NiaNavRail(
                            destinations = appState.topLevelDestinations,
                            onNavigateToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                            modifier = Modifier
                                .testTag("NiaNavRail")
                                .safeDrawingPadding(),
                        )
                    }

                    Column(Modifier.fillMaxSize()) {
                        // Show the top app bar on top level destinations.
                        val destination = appState.currentTopLevelDestination
                        if (destination != null) {
//                            NiaTopAppBar(
//                                titleRes = destination.titleTextId,
//                                navigationIcon = NiaIcons.Search,
//                                navigationIconContentDescription = stringResource(
//                                    id = settingsR.string.top_app_bar_navigation_icon_description,
//                                ),
//                                actionIcon = NiaIcons.Settings,
//                                actionIconContentDescription = stringResource(
//                                    id = settingsR.string.top_app_bar_action_icon_description,
//                                ),
//                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                                    containerColor = Color.Transparent,
//                                ),
//                                onActionClick = { showSettingsDialog = true },
//                                onNavigationClick = { appState.navigateToSearch() },
//                            )
                        }

                        NiaNavHost(appState = appState, onShowSnackbar = { message, action ->
                            snackbarHostState.showSnackbar(
                                message = message,
                                actionLabel = action,
                                duration = Short,
                            ) == ActionPerformed
                        })
                    }

                    // TODO: We may want to add padding or spacer when the snackbar is shown so that
                    //  content doesn't display behind it.
                }
            }
        }
    }
}

@Composable
private fun NiaNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
//    NiaNavigationRail(modifier = modifier) {
//        destinations.forEach { destination ->
//            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
//            val hasUnread = destinationsWithUnreadResources.contains(destination)
//            NiaNavigationRailItem(
//                selected = selected,
//                onClick = { onNavigateToDestination(destination) },
//                icon = {
//                    Icon(
//                        imageVector = destination.unselectedIcon,
//                        contentDescription = null,
//                    )
//                },
//                selectedIcon = {
//                    Icon(
//                        imageVector = destination.selectedIcon,
//                        contentDescription = null,
//                    )
//                },
//                label = { Text(stringResource(destination.iconTextId)) },
//                modifier = if (hasUnread) Modifier.notificationDot() else Modifier,
//            )
//        }
//    }
}

@Composable
private fun NiaBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
//    NiaNavigationBar(
//        modifier = modifier,
//    ) {
//        destinations.forEach { destination ->
//            val hasUnread = destinationsWithUnreadResources.contains(destination)
//            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
//            println("CLICK NAVIGATION BAR ITEM $selected 4")
//            NiaNavigationBarItem(
//                selected = selected,
//                onClick = { onNavigateToDestination(destination) },
//                icon = {
//                    Icon(
//                        imageVector = destination.unselectedIcon,
//                        contentDescription = null,
//                    )
//                },
//                selectedIcon = {
//                    Icon(
//                        imageVector = destination.selectedIcon,
//                        contentDescription = null,
//                    )
//                },
//                label = { Text(stringResource(destination.iconTextId)) },
//                modifier = if (hasUnread) Modifier.notificationDot() else Modifier,
//            )
//        }
//    }
}

private fun Modifier.notificationDot(): Modifier =
    composed {
        val tertiaryColor = MaterialTheme.colorScheme.tertiary
        drawWithContent {
            drawContent()
            drawCircle(
                tertiaryColor,
                radius = 5.dp.toPx(),
                // This is based on the dimensions of the NavigationBar's "indicator pill";
                // however, its parameters are private, so we must depend on them implicitly
                // (NavigationBarTokens.ActiveIndicatorWidth = 64.dp)
                center = center + Offset(
                    64.dp.toPx() * .45f,
                    32.dp.toPx() * -.45f - 6.dp.toPx(),
                ),
            )
        }
    }

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
