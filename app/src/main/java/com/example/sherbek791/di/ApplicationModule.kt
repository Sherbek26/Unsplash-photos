package com.example.sherbek791.di

import android.content.Context
import androidx.room.Room
import com.example.dependancyinjection.utility.NetworkHelper
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.database.AppDatabase
import com.example.sherbek791.database.DatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) : Context{
        return context
    }

    @Singleton
    @Provides
    fun provideApiService() : ApiService{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"app_databse")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabaseDao(appDatabase: AppDatabase) : DatabaseDao{
        return appDatabase.databaseDao()
    }
}