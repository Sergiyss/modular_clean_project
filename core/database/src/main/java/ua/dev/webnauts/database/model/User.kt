package ua.dev.webnauts.database.model

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

data class User(val id : Int, val userName : String, val userEmail : String)

