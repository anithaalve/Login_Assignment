package com.example.loginassignment.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginassignment.data.repository.RegisterRepository

class LoginViewModelFactory(private val repository: RegisterRepository) :
  ViewModelProvider.Factory {
  @Suppress("Unchecked_cast")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
      return LoginViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown View Model Class")
  }
}