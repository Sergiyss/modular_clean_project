package ua.dev.webnauts.network.ktor

import ua.dev.webnauts.network.data.randomuser.RandomUserResponse

interface ServiceApi {
   suspend fun randomUser(): NetworkResponse<RandomUserResponse>

}