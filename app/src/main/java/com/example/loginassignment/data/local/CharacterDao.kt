package com.example.loginassignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.loginassignment.data.entities.CharacterEntity

@Dao
interface CharacterDao {
  
  @Query("SELECT * FROM characters")
  fun getAllCharacters(): LiveData<List<CharacterEntity>>
  
  @Query("SELECT * FROM characters WHERE id = :id")
  fun getCharacter(id: Int): LiveData<CharacterEntity>
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(characters: List<CharacterEntity>)
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(character: CharacterEntity)
}