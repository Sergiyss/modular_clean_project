package ua.dev.webnauts.modularcleandesign

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ua.dev.webnauts.UserDataBase
import ua.dev.webnauts.database.DatabaseUser
import ua.dev.webnauts.database.di.DataBase.provideAndroidPartyDatabaseFactoryDriver
import ua.dev.webnauts.database.model.UserDatabaseManagement
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val databaseUser : DatabaseUser,
    private val userDatabaseManagement : UserDatabaseManagement
) : ViewModel() {

    fun user(number: Int){
       databaseUser.databaseQueries.insertUser(
            1, "kkkk", "llllll 000 asdasd $number"
       )
    }

    suspend fun getUseFlow() =
        userDatabaseManagement.getUserFlow()
}