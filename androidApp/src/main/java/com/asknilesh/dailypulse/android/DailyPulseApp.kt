package com.asknilesh.dailypulse.android

import android.app.Application
import com.asknilesh.dailypulse.android.di.databaseModule
import com.asknilesh.dailypulse.android.di.viewModelsModule
import com.asknilesh.dailypulse.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DailyPulseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule + databaseModule
        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}