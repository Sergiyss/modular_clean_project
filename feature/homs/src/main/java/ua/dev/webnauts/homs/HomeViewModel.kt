package ua.dev.webnauts.homs

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.dev.webnauts.network.data.randomuser.RandomUserResponse
import ua.dev.webnauts.network.ktor.NetworkResponse
import ua.dev.webnauts.network.ktor.ServiceApi
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val serviceApi: ServiceApi,
) : ViewModel() {

    var randomUser = mutableStateOf<NetworkResponse<RandomUserResponse>?>(null)
        private set


    //Получение рандомного пользователя

    fun getRandomUser(){
        viewModelScope.launch(Dispatchers.IO) {
            randomUser.value = NetworkResponse.Loading()
            randomUser.value = serviceApi.randomUser()
        }
    }
}