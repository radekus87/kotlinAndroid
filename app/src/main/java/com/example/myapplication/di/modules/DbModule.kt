package com.example.myapplication.di.modules

import androidx.room.Room
import com.example.myapplication.data.local.db.MainDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val dbModule = module {
    single{
        Room.databaseBuilder(
            androidApplication(),
            MainDatabase::class.java,
            "database_name"
        ).build()
    }

    factory {
        get<MainDatabase>().bookDao()
    }
}