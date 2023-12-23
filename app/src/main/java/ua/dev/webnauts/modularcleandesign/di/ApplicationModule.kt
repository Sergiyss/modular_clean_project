package ua.dev.webnauts.modularcleandesign.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.dev.webnauts.database.DatabaseUser
import ua.dev.webnauts.database.di.DataBase
import ua.dev.webnauts.database.model.UserDatabaseManagement
import ua.dev.webnauts.modularcleandesign.App
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplication(
        @ApplicationContext context : Context
    ): App {
        return context as App
    }

    /**
     * Потому что di тянет зависимости с другого модуля
     * */
//    @Provides
//    @Singleton
//    fun provideAndroidPartyDatabaseFactoryDriver(
//        context: Context
//    ): DatabaseUser = DataBase.provideAndroidPartyDatabaseFactoryDriver(context)
//
//
//    @Provides
//    @Singleton
//    fun provideUserDatabaseManagement(databaseUser : DatabaseUser) =
//        UserDatabaseManagement(databaseUser)

}