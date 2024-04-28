package com.example.sherbek791.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sherbek791.database.entities.DatabaseEntity

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheImages(list: DatabaseEntity)

    @Query("SELECT * FROM DatabaseEntity")
    fun getImages() : DatabaseEntity

    @Query("DELETE FROM DatabaseEntity WHERE DatabaseEntity.id = :itemId")
    fun deleteItem(itemId : Int)
}