package com.example.sherbek791.database.entities

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val image : Bitmap?,
)
