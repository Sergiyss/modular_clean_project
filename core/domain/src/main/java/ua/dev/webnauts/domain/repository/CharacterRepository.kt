package ua.dev.webnauts.domain.repository

import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun <T>getCharacters(): Flow<T>
}