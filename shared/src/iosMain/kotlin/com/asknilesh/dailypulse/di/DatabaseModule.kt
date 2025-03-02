package com.asknilesh.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.asknilesh.dailypulse.db.DailyPulseDatabase
import com.asknilesh.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver>{
        DatabaseDriverFactory().createDriver()
    }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}