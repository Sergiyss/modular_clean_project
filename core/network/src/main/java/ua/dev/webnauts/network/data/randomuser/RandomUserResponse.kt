package ua.dev.webnauts.network.data.randomuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomUserResponse(
    @SerialName("info")
    val info: Info = Info(),
    @SerialName("results")
    val results: List<Result> = listOf()
)