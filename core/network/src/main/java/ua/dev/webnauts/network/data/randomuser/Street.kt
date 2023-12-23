package ua.dev.webnauts.network.data.randomuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Street(
    @SerialName("name")
    val name: String = "",
    @SerialName("number")
    val number: Int = 0
)