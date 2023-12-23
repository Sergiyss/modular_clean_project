package ua.dev.webnauts.network.di


import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.client.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.cache.storage.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import ua.dev.webnauts.network.ktor.ServiceApi
import ua.dev.webnauts.network.ktor.ServiceApiImpl
import java.nio.file.*

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideClient(): HttpClient {
        return HttpClient(Android) {

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL

            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = false
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
            install(Auth) {
                bearer {}
            }
        }
    }

    @Provides
    @Singleton
    fun provideServiceApi(serviceApiImpl: ServiceApiImpl): ServiceApi = serviceApiImpl

}