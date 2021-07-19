package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.modules.appModule
import com.example.myapplication.di.modules.dbModule
import com.example.myapplication.di.modules.networkModule
import com.example.myapplication.di.modules.repositoryModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            modules(dbModule, networkModule, appModule, repositoryModule)
        }
    }
}