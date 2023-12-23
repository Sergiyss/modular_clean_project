package ua.dev.webnauts.network.data.randomuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: Int = 0,
    @SerialName("seed")
    val seed: String = "",
    @SerialName("version")
    val version: String = ""
)