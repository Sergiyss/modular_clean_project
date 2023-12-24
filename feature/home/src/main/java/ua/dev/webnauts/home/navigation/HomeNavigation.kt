package ua.dev.webnauts.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import ua.dev.webnauts.home.MainScreen



const val HOME = "home_screen"

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(HOME, navOptions)
}

fun NavGraphBuilder.homeScreen() {
    composable(
        route = HOME,
    ) {
        MainScreen()
    }
}
