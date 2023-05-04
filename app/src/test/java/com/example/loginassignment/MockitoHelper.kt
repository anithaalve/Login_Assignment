package com.example.loginassignment

import org.mockito.Mockito

object MockitoHelper {
  fun <T> anyObject(): T {
    Mockito.any<T>()
    return uninitialized()
  }

  private fun <T> uninitialized(): T =  null as T
}