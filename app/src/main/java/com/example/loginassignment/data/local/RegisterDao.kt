package com.example.loginassignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.loginassignment.data.entities.RegisterEntity

@Dao
interface RegisterDao {
  
  @Insert
  suspend fun insert(register: RegisterEntity)
  
  @Delete
  suspend fun deleteSubscriber(register: RegisterEntity): Int
  
  @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
  fun getAllUsers(): LiveData<List<RegisterEntity>>
  
  @Query("DELETE FROM Register_users_table")
  suspend fun deleteAll(): Int
  
  @Query("SELECT * FROM Register_users_table WHERE user_name LIKE :userName")
  suspend fun getUsername(userName: String): RegisterEntity?
}