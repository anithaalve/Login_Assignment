package com.example.loginassignment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginassignment.data.entities.CharacterEntity
import com.example.loginassignment.data.entities.RegisterEntity

@Database(
  entities = [CharacterEntity::class, RegisterEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  
  abstract fun characterDao(): CharacterDao
  
  abstract fun registerDatabaseDao(): RegisterDao
  
  companion object {
    @Volatile
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase =
      instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

    private fun buildDatabase(appContext: Context) =
      Room.databaseBuilder(appContext, AppDatabase::class.java, "characters")
        .fallbackToDestructiveMigration()
        .build()
  }
}