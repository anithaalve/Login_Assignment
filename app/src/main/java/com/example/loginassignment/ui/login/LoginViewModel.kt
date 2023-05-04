package com.example.loginassignment.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginassignment.R
import com.example.loginassignment.common.Event
import com.example.loginassignment.common.SingleLiveEvent
import com.example.loginassignment.data.repository.RegisterRepository
import com.google.android.gms.common.util.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: RegisterRepository) : ViewModel(), View.OnClickListener {
  
  val mUsername = SingleLiveEvent<String>()
  val mPassword = SingleLiveEvent<String>()
  private val viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
  
  private val mNavigateToRegister = MutableLiveData<Event<Boolean>>()
  private val mNavigateToUserDetails = MutableLiveData<Event<Boolean>>()
  private val mShowErrorLiveData = MutableLiveData<Event<Int>>()
  //private lateinit var repository: RegisterRepository
  
  fun getNavigateToRegister(): LiveData<Event<Boolean>> {
    return mNavigateToRegister
  }
  
  fun getNavigatetoUserDetails(): LiveData<Event<Boolean>> {
    return mNavigateToUserDetails
  }
  
  fun getShowErrorLiveData(): LiveData<Event<Int>> {
    return mShowErrorLiveData
  }
  
  fun getUsername(): MutableLiveData<String> {
    return mUsername
  }
  
  fun getPassword(): MutableLiveData<String> {
    return mPassword
  }
  
  override fun onClick(v: View) {
    if (v.id == R.id.login_button) {
      if (Strings.isEmptyOrWhitespace(mUsername.value) || Strings.isEmptyOrWhitespace(mPassword.value)) {
        mShowErrorLiveData.postValue(Event(1))
      } else {
        uiScope.launch {
          val usersNames = repository.getUserName(mUsername.value!!)
          if (usersNames != null) {
            if (usersNames.passwrd == mPassword.value) {
              mUsername.value = null
              mPassword.value = null
              mNavigateToUserDetails.postValue(Event(true))
            } else {
              mShowErrorLiveData.postValue(Event(3))
            }
          } else {
            mShowErrorLiveData.postValue(Event(2))
          }
        }
      }
    } else if (v.id == R.id.signup_button) {
      mNavigateToRegister.postValue(Event(true))
    }
  }

  fun doneNavigatingRegiter() {
    mNavigateToRegister.postValue(Event(false))
  }
  
  fun doneNavigatingUserDetails() {
    mNavigateToUserDetails.postValue(Event(false))
  }
}