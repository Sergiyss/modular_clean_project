package ua.dev.webnauts.network.ktor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ua.dev.webnauts.network.data.randomuser.RandomUserResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Описываю общее поведение для обращения к серверу
 *
 * */
@Singleton
class ServiceApiImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val client: HttpClient
) : ServiceApi, SafeApiCall() {
    override suspend fun randomUser(): NetworkResponse<RandomUserResponse> {
        return safeCall(context) {
            client.get(HttpRoutes.randomUserApi) {
                contentType(ContentType.Application.Json)
            }
        }
    }
}