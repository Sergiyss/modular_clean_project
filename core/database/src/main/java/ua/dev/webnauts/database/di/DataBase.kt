package ua.dev.webnauts.database.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.dev.webnauts.database.DatabaseUser
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBase {

    @Provides
    @Singleton
    fun provideAndroidPartyDatabaseFactoryDriver(
        context: Context
    ): DatabaseUser {
        val database = AndroidSqliteDriver(DatabaseUser.Schema, context, "DatabaseUser.db")
        return DatabaseUser(database)
    }

}