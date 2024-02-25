package ua.dev.webnauts.network.ktor

import ua.dev.webnauts.network.model.character.CharacterDto

interface ServiceApi {
   suspend fun getCharacter(page : Int): NetworkResponse<CharacterDto>

}