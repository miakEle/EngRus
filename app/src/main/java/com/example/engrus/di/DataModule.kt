package com.example.engrus.di

import android.content.Context
import androidx.room.Room
import com.example.engrus.data.AppDataBase
import com.example.engrus.data.WordCardDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): AppDataBase = Room.databaseBuilder(
        context = context,
        AppDataBase::class.java,
        "app_db"
    ).build()

    @Provides
    @Singleton
    fun provideWordCardDao(db: AppDataBase) : WordCardDao = db.wordCardDao()
}