package ua.dev.webnauts.modularcleandesign

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.dev.webnauts.database.DatabaseUser
import ua.dev.webnauts.database.model.UserDatabaseManagement
import ua.dev.webnauts.network.ktor.ServiceApi
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val databaseUser : DatabaseUser,
    private val userDatabaseManagement : UserDatabaseManagement,
    private val serviceApi: ServiceApi,
) : ViewModel() {





    fun user(number: Int){
       databaseUser.databaseQueries.insertUser(
            1, "kkkk", "llllll 1111 asdasd $number"
       )
    }

    suspend fun getUseFlow() =
        userDatabaseManagement.getUserFlow()


}

enum class ThemeBrand {
    DEFAULT, ANDROID
}

enum class DarkThemeConfig {
    FOLLOW_SYSTEM, LIGHT, DARK
}


/**
 * Class summarizing user interest data
 */
data class UserData(
    val bookmarkedNewsResources: Set<String>,
    val viewedNewsResources: Set<String>,
    val followedTopics: Set<String>,
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
    val shouldHideOnboarding: Boolean,
)

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}