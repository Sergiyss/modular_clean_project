package ua.dev.webnauts.modularcleandesign.app

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.os.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ua.dev.webnauts.modularcleandesign.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ua.dev.webnauts.ui.designsystem.TrackDisposableJank
import ua.dev.webnauts.utils.util.NetworkMonitor

@Composable
fun rememberNiaAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): NiaAppState {
    NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor,
    ) {
        NiaAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            networkMonitor,
        )
    }
}

@Stable
class NiaAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
//            forYouNavigationRoute -> FOR_YOU
//            bookmarksRoute -> BOOKMARKS
//            interestsRoute -> INTERESTS
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    /**
     * Карта пунктов назначения верхнего уровня для использования в TopBar, BottomBar и NavRail. Ключом является
     *маршрут.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()



    /**
     * Логика пользовательского интерфейса для перехода к пункту назначения верхнего уровня в приложении.
     * только одна копия места назначения обратного стека, а также сохранение и восстановление состояния всякий раз, когда вы
     * перейти к нему и обратно.
     *
     * @param topLevelDestination: пункт назначения, к которому должно перейти приложение.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Всплывающее окно к начальному пункту назначения графика
                // избегаем создания большого стека пунктов назначения
                // в заднем стеке, когда пользователи выбирают элементы
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Избегайте нескольких копий одного и того же места назначения, когда
                // повторный выбор того же элемента
                launchSingleTop = true
                // Восстанавливаем состояние при повторном выборе ранее выбранного элемента
                restoreState = true
            }

//            when (topLevelDestination) {
//                FOR_YOU -> navController.navigateToForYou(topLevelNavOptions)
//                BOOKMARKS -> navController.navigateToBookmarks(topLevelNavOptions)
//                INTERESTS -> navController.navigateToInterestsGraph(topLevelNavOptions)
//            }
        }
    }

    fun navigateToSearch() {
       // navController.navigateToSearch()
    }
}

/**
 * Хранит информацию о событиях навигации, которые будут использоваться с JankStats.
 */
@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->

            //metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
