package com.example.sherbek791.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sherbek791.database.entities.DatabaseEntity

@Database(entities = [
    DatabaseEntity::class
], version = 2, exportSchema = false)
@TypeConverters(RoomConvertor::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun databaseDao() : DatabaseDao
}