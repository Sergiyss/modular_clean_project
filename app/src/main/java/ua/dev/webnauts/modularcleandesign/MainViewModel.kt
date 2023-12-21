package ua.dev.webnauts.modularcleandesign

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import ua.dev.webnauts.database.di.DataBase.provideAndroidPartyDatabaseFactoryDriver

class MainViewModel() : ViewModel() {

    fun user(context : Context){
        val aa = provideAndroidPartyDatabaseFactoryDriver(context).databaseQueries.insertUser(
            1, "kkkk", "llllll 000 asdasd"
        )
    }
}