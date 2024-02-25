package ua.dev.webnauts.network.ktor

object HttpRoutes {
    private const val BASE_URL_STAJ = "https://rickandmortyapi.com/api"
    private const val BASE_URL_TEST = "https://google.com.ua"
    private const val BASE_URL_COM = "https://google.com.ua"

    const val BASE_URL = BASE_URL_STAJ

    val  characterRoute = "$BASE_URL/character/"

}