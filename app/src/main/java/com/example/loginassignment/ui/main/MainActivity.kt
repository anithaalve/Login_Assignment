package com.example.loginassignment.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.loginassignment.BR
import com.example.loginassignment.R
import com.example.loginassignment.app.base.BaseActivity
import com.example.loginassignment.common.Constant.Companion.NAVIGATE_UP
import com.example.loginassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
  OnDestinationChangedListener {
  
  private lateinit var mToolBarTitleTV: TextView
  
  override fun bindingVariable(): Int {
    return BR.mainViewModel
  }

  override fun getViewModel(): Class<MainViewModel> {
    return MainViewModel::class.java
  }
  
  /**
   * This method returns layout mId of the [MainActivity] view.
   *
   * @return layout id of integer type.
   */
  override fun layoutRes(): Int {
    return R.layout.activity_main
  }
  
  override fun setupNavigation() {
    val toolbar: Toolbar = dataBinding.tbLayout.toolbar
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(false)
    supportActionBar?.setHomeButtonEnabled(true)
    mToolBarTitleTV = dataBinding.tbLayout.tvAppbarTitle
    mNavController = Navigation.findNavController(this, R.id.fragment_main)
    mNavController.addOnDestinationChangedListener(this)
    setUpDestinations()
    initListeners()
  }

  private fun initListeners() {
    mViewModel.getNavigationLiveData().observe(this, {
      this.navigateFragment(it)
    })
  }
  
  override fun onDestroy() {
    mViewModel.getNavigationLiveData().removeObservers(this)
    mNavController.let {  mNavController.removeOnDestinationChangedListener(this) }
    super.onDestroy()
  }
  
  override fun onDestinationChanged(
    controller: NavController,
    destination: NavDestination,
    arguments: Bundle?
  ) {
    //mDataBinding.tbLayout.ivAppBarClose.setVisibility(View.GONE)
    when (destination.id) {
      R.id.loginFragment -> {
        mToolBarTitleTV.visibility = View.VISIBLE
        mToolBarTitleTV.text = getAppTitle(R.string.Login_button)
      }
      R.id.registerFragment -> {
        mToolBarTitleTV.visibility = View.VISIBLE
        mToolBarTitleTV.text = getAppTitle(R.string.submitButton)
      }
      R.id.characterDetailFragment -> {
        mToolBarTitleTV.visibility = View.VISIBLE
        mToolBarTitleTV.text = getAppTitle(R.string.character_detail)
      }
      R.id.charactersFragment -> {
        mToolBarTitleTV.visibility = View.VISIBLE
        mToolBarTitleTV.text = getAppTitle(R.string.character_list)
      }
      else -> {
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    navigateFragment(NAVIGATE_UP)
    return true
  }
  
  override fun onBackPressed() {
    onNavigateUp()
  }
  
  override fun onNavigateUp(): Boolean {
    return mNavController.navigateUp() || super.onNavigateUp()
  }

  /**
   * For Navigating between fragments
   *
   * @param id int.
   */
  private fun navigateFragment(id: Int) {
    if (id == NAVIGATE_UP) {
      mNavController.navigateUp()
    } else {
      mNavController.navigate(id)
    }
  }
  
  /**
   * Back arrow will be disabled in the following mentioned destinations
   */
  private fun setUpDestinations() {
    val topLevelDestinations: MutableSet<Int> = HashSet()
    topLevelDestinations.add(R.id.loginFragment)
   
    val appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).build()
    setupActionBarWithNavController(this, mNavController, appBarConfiguration)
  }
}
