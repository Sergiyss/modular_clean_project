package ua.dev.webnauts.network.data.randomuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("city")
    val city: String = "",
    @SerialName("coordinates")
    val coordinates: Coordinates = Coordinates(),
    @SerialName("country")
    val country: String = "",

    @SerialName("state")
    val state: String = "",
    @SerialName("street")
    val street: Street = Street(),
    @SerialName("timezone")
    val timezone: Timezone = Timezone()
)