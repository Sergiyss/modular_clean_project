package ua.dev.webnauts.network.model.character

enum class CharacterStatus {
    ALIVE, DEAD, UNKNOWN
}

fun getCharacterStatusEnum(status: String) =
    when (status) {
        "Alive" -> CharacterStatus.ALIVE
        "Dead" -> CharacterStatus.DEAD
        else -> CharacterStatus.UNKNOWN
    }