package com.example.loginassignment.ui.register

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginassignment.common.Event
import com.example.loginassignment.common.SingleLiveEvent
import com.example.loginassignment.data.entities.RegisterEntity
import com.example.loginassignment.data.repository.RegisterRepository
import com.google.android.gms.common.util.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
  private val repository: RegisterRepository
) : ViewModel(), View.OnClickListener {
  private val mFirstName = SingleLiveEvent<String>()
  private val mLastName = SingleLiveEvent<String>()
  private val mUsername = SingleLiveEvent<String>()
  private val mPassword = SingleLiveEvent<String>()
  
  private val mNavigationLiveData = MutableLiveData<Event<Boolean>>()
  private val mShowErrorLiveData = MutableLiveData<Event<Int>>()
  
  private val viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
  
  fun getNavigationLiveData(): LiveData<Event<Boolean>> {
    return mNavigationLiveData
  }
  
  fun getShowErrorLiveData(): LiveData<Event<Int>> {
    return mShowErrorLiveData
  }
  
  fun getFirstName(): SingleLiveEvent<String> {
    return mFirstName
  }
  
  fun getLastName(): SingleLiveEvent<String> {
    return mLastName
  }
  
  fun getUsername(): SingleLiveEvent<String> {
    return mUsername
  }
  
  fun getPassword(): SingleLiveEvent<String> {
    return mPassword
  }
  
  fun doneNavigating() {
    mNavigationLiveData.postValue(Event(false))
  }
  
  private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
    repository.insert(user)
  }
  
  override fun onClick(v: View?) {
    if (Strings.isEmptyOrWhitespace(mFirstName.value) ||
      Strings.isEmptyOrWhitespace(mLastName.value) ||
      Strings.isEmptyOrWhitespace(mUsername.value) ||
      Strings.isEmptyOrWhitespace(mPassword.value)
    ) {
      mShowErrorLiveData.postValue(Event(1))
    } else {
      uiScope.launch {
        val usersNames = repository.getUserName(mUsername.value!!)
        if (usersNames != null) {
          mShowErrorLiveData.postValue(Event(4))
        } else {
          //CustomBinding
          val firstName = mFirstName.value!!
          val lastName = mLastName.value!!
          val email = mUsername.value!!
          val password = mPassword.value!!
          insert(RegisterEntity(0, firstName, lastName, email, password))
          mFirstName.value = null
          mLastName.value = null
          mUsername.value = null
          mPassword.value = null
          mNavigationLiveData.postValue(Event(true))
        }
      }
    }
  }
}