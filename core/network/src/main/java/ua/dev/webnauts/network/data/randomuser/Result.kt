package ua.dev.webnauts.network.data.randomuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("cell")
    val cell: String = "",
    @SerialName("dob")
    val dob: Dob = Dob(),
    @SerialName("email")
    val email: String = "",
    @SerialName("gender")
    val gender: String = "",
    @SerialName("id")
    val id: Id = Id(),
    @SerialName("location")
    val location: Location = Location(),
    @SerialName("login")
    val login: Login = Login(),
    @SerialName("name")
    val name: Name = Name(),
    @SerialName("nat")
    val nat: String = "",
    @SerialName("phone")
    val phone: String = "",
    @SerialName("picture")
    val picture: Picture = Picture(),
    @SerialName("registered")
    val registered: Registered = Registered()
)