package com.example.sherbek791.api

import com.example.sherbek791.screens.category.modules.CategoryModule
import com.example.sherbek791.screens.home.module.PhotosModule
import com.example.sherbek791.screens.insideCategory.module.TopicModule
import com.example.sherbek791.screens.insideCategory.module.topicPhotos.TopicPhotosModule
import com.example.sherbek791.screens.owner.module.ProfileModule
import com.example.sherbek791.screens.owner.module.ProfilePicturesModule
import com.example.sherbek791.screens.search.modules.photos.SearchPhotosModule
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("topics")
    suspend fun getCategories(
        @Query("client_id") clientId : String,
        @Query("page") page : Int,
    ) : CategoryModule

    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("order_by") orderBy : String
    ) : PhotosModule

    @GET("search/photos")
    suspend fun getSearchPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("query") query : String
    ) : SearchPhotosModule


    @GET("users/{username}")
    fun getProfile(
        @Path("username") username : String,
        @Query("client_id") clientId: String
    ) : Call<ProfileModule>

    @GET("users/{username}/photos")
    suspend fun getProfilePictures(
        @Path("username") username: String,
        @Query("client_id") clientId: String,
        @Query("page") page: Int
    ) : ProfilePicturesModule

    @GET("topics/{slug}")
    fun getTopic(
        @Path("slug") slug : String,
        @Query("client_id") clientId : String
    ) : Call<TopicModule>

    @GET("topics/{slug}/photos")
    suspend fun getTopicPhotos(
        @Path("slug") slug: String,
        @Query("client_id") clientId : String,
        @Query("page") page: Int
    ) : TopicPhotosModule

}