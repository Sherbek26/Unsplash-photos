package com.example.sherbek791.screens.image.repository

import com.example.sherbek791.database.DatabaseDao
import com.example.sherbek791.database.entities.DatabaseEntity
import javax.inject.Inject

class ImageRepository @Inject constructor(private val databaseDao: DatabaseDao) {
    suspend fun insertImage(image: DatabaseEntity) {
        databaseDao.cacheImages(image)
    }

//    suspend fun getImage(url: String): DatabaseEntity? {
//        return databaseDao.getImages(url)
//    }
}