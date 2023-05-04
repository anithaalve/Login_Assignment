package com.example.loginassignment.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.loginassignment.data.local.AppDatabase
import com.example.loginassignment.data.local.CharacterDao
import com.example.loginassignment.data.local.RegisterDao
import com.example.loginassignment.data.remote.CharacterRemoteDataSource
import com.example.loginassignment.data.remote.CharacterService
import com.example.loginassignment.data.repository.CharacterRepository
import com.example.loginassignment.data.repository.RegisterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public class AppModule {
  
  @Singleton
  @Provides
  fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
    .baseUrl("https://rickandmortyapi.com/api/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
  
  @Singleton
  @Provides
  fun provideGson(): Gson = GsonBuilder().create()
  
  @Singleton
  @Provides
  fun provideCharacterService(retrofit: Retrofit): CharacterService =
    retrofit.create(CharacterService::class.java)
  
  @Singleton
  @Provides
  fun provideCharacterRemoteDataSource(characterService: CharacterService) =
    CharacterRemoteDataSource(characterService)
  
  /////////
  @Singleton
  @Provides
  fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)
  
  @Singleton
  @Provides
  fun provideCharacterDao(db: AppDatabase) = db.characterDao()
  
  @Singleton
  @Provides
  fun provideRegisterDatabaseDao(db: AppDatabase) = db.registerDatabaseDao()
  
  //////////////////
  @Singleton
  @Provides
  fun provideRepository(
    remoteDataSource: CharacterRemoteDataSource,
    localDataSource: CharacterDao
  ) = CharacterRepository(remoteDataSource, localDataSource)
  
  @Singleton
  @Provides
  fun provideRegisterRepository(localDataSource: RegisterDao) =
    RegisterRepository(localDataSource)
  
  /////
  /**
   * Provides Application Context.
   *
   * @param application [Application].
   * @return [Context].
   */
  @Provides
  @Singleton
  fun provideAppContext(application: Application): Context {
    return application.applicationContext
  }
  
  /**
   * Provides Application Resources.
   *
   * @param context [Context].
   * @return [Resources].
   */
  @Provides
  @Singleton
  fun provideResource(context: Context): Resources {
    return context.resources
  }
}