package com.example.loginassignment.ui.main

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import com.example.loginassignment.common.Constant
import com.example.loginassignment.common.SingleLiveEvent
import com.example.loginassignment.data.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val repository: RegisterRepository
)  : ViewModel(), View.OnClickListener {
  private val mNavigationLiveData = SingleLiveEvent<Int>()
  private lateinit var mNavDestination: NavDestination
  
  /**
   * Observer used to update Navigation
   *
   * @return mNavigationLiveData Live Integer variable
   */
  fun getNavigationLiveData(): SingleLiveEvent<Int> {
    return mNavigationLiveData
  }
  
  /**
   * Method to handle click events from the View.
   *
   * @param view View
   */
  override fun onClick(view: View) {
    mNavigationLiveData.postValue(Constant.NAVIGATE_UP)
  }
}
