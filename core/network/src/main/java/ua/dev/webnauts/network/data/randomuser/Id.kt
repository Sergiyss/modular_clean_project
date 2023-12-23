package ua.dev.webnauts.network.data.randomuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Id(
    @SerialName("name")
    val name: String = "",
    @SerialName("value")
    val value: String? = null
)