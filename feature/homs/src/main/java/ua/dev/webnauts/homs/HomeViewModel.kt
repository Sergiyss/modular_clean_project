package ua.dev.webnauts.homs

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.dev.webnauts.network.ktor.NetworkResponse
import ua.dev.webnauts.network.ktor.ServiceApi
import ua.dev.webnauts.network.model.character.CharacterDto
import ua.dev.webnauts.network.model.character.ResultDto
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val serviceApi: ServiceApi,
) : ViewModel() {

    var result = mutableStateOf<List<ResultDto>?>(null)
        private set

    var randomUser = mutableStateOf<NetworkResponse<CharacterDto>?>(null)
        private set

    var page = 1


    //Получение рандомного пользователя

    fun getRandomUser(){
        viewModelScope.launch(Dispatchers.IO) {
            if(result == null) {
                randomUser.value = NetworkResponse.Loading()
            }

            page++

            println("PAGE: $page")
            serviceApi.getCharacter(page).also {response->
                if (response is NetworkResponse.Success){
                    result.value = result.value.orEmpty() + response.data.results

                    randomUser.value = NetworkResponse.Success(CharacterDto().copy(
                        results = result.value!!
                    ))
                }
            }
        }
    }
}