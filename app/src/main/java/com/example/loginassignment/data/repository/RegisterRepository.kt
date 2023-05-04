package com.example.loginassignment.data.repository

import com.example.loginassignment.data.entities.RegisterEntity
import com.example.loginassignment.data.local.RegisterDao
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val dao: RegisterDao) {
  val users = dao.getAllUsers()
  suspend fun insert(user: RegisterEntity) {
    return dao.insert(user)
  }
  
  suspend fun getUserName(userName: String): RegisterEntity? {
    return dao.getUsername(userName)
  }
  
  suspend fun deleteAll(): Int {
    return dao.deleteAll()
  }
}