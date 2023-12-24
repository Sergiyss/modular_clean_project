package ua.dev.webnauts.modularcleandesign

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import ua.dev.webnauts.database.DatabaseUser
import ua.dev.webnauts.database.model.UserDatabaseManagement
import ua.dev.webnauts.network.data.randomuser.RandomUserResponse
import ua.dev.webnauts.network.ktor.NetworkResponse
import ua.dev.webnauts.network.ktor.ServiceApi

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val databaseUser : DatabaseUser,
    private val userDatabaseManagement : UserDatabaseManagement,
    private val serviceApi: ServiceApi,
) : ViewModel() {

    var randomUser = mutableStateOf<NetworkResponse<RandomUserResponse>?>(null)
        private set



    fun user(number: Int){
       databaseUser.databaseQueries.insertUser(
            1, "kkkk", "llllll 1111 asdasd $number"
       )
    }

    suspend fun getUseFlow() =
        userDatabaseManagement.getUserFlow()

    //Получение рандомного пользователя

    fun getRandomUser(){
        viewModelScope.launch(Dispatchers.IO) {
            randomUser.value = NetworkResponse.Loading()
            randomUser.value = serviceApi.randomUser()
        }
    }
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