package ua.dev.webnauts.network.model.character


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("info")
    val info: InfoDto = InfoDto(),
    @SerialName("results")
    val results: List<ResultDto> = listOf()
)

@Serializable
data class InfoDto(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("next")
    val next: String = "",
    @SerialName("pages")
    val pages: Int = 0,
    @SerialName("prev")
    val prev: String = ""
)

@Serializable
data class LocationDto(
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)

@Serializable
data class OriginDto(
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)

@Serializable
data class ResultDto(
    @SerialName("created")
    val created: String = "",
    @SerialName("episode")
    val episode: List<String> = listOf(),
    @SerialName("gender")
    val gender: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("image")
    val image: String = "",
    @SerialName("location")
    val location: LocationDto = LocationDto(),
    @SerialName("name")
    val name: String = "",
    @SerialName("origin")
    val origin: OriginDto = OriginDto(),
    @SerialName("species")
    val species: String = "",
    @SerialName("status")
    val status: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("url")
    val url: String = ""
)