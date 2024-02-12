package ua.dev.webnauts.modularcleandesign.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import ua.dev.webnauts.modularcleandesign.App
import ua.dev.webnauts.utils.util.AppDispatchers
import ua.dev.webnauts.utils.util.ConnectivityManagerNetworkMonitor
import ua.dev.webnauts.utils.util.NetworkMonitor
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


    @Provides
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor {
        return ConnectivityManagerNetworkMonitor(context)
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

    //Dispatchers
    @Provides
    fun  provideAppDispatchers(): AppDispatchers = AppDispatchers()

}