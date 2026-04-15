package com.example.engrus.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WordCardDbModel::class], version = 2, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun wordCardDao(): WordCardDao

}