package ua.dev.webnauts.database.model

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.currentCoroutineContext
import ua.dev.webnauts.database.DatabaseUser
import javax.inject.Inject

class UserDatabaseManagement @Inject constructor(
    private val database: DatabaseUser
) {
    /**
     * Флоу функция для обновления данных в реальном времени
     *
     * Подходит для перезаписи данных в пользовательском интерфейсе
     * */
    suspend fun getUserFlow() =
        database.databaseQueries.allUsers()
            .asFlow()
            .mapToList(currentCoroutineContext())

}