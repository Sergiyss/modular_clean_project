package ua.dev.webnauts.network.ktor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parameters
import ua.dev.webnauts.network.model.character.CharacterDto
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

    override suspend fun getCharacter(page: Int): NetworkResponse<CharacterDto> {
        return safeCall(context) {
            client.get(HttpRoutes.characterRoute) {
                parameters {
                    parameter("page", page)
                }
                contentType(ContentType.Application.Json)
            }
        }
    }
}