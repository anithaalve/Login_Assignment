package com.example.loginassignment.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

abstract class BaseActivity<V : ViewModel, D : ViewDataBinding> :
  AppCompatActivity() {
  lateinit var dataBinding: D
  protected lateinit var mViewModel: V
  
  protected lateinit var mNavController: NavController
  
  /**
   * Override for set binding variable
   *
   * @return variable id
   */
  protected abstract fun bindingVariable(): Int
  
  /**
   * This method is called within the child fragment. Subclasses must override this method to
   * return the View Model Class of child fragment.
   *
   * @return [Class]
   */
  protected abstract fun getViewModel(): Class<V>
  
  /**
   * This method is called within the child activity. Subclasses must override this method to
   * return the layout mId for initializing child activities data binding object.
   *
   * @return layout mId of integer type.
   */
  @LayoutRes
  protected abstract fun layoutRes(): Int
  
  protected abstract fun setupNavigation()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dataBinding = DataBindingUtil.setContentView(this, layoutRes())
    mViewModel = ViewModelProvider(this).get(getViewModel())
    dataBinding.setVariable(bindingVariable(), mViewModel)
    dataBinding.setLifecycleOwner(this)
    dataBinding.executePendingBindings()
    setupNavigation()
  }
  
  /**
   * clears the resources when activity destroys
   */
  public override fun onDestroy() {
    dataBinding.unbind()
    super.onDestroy()
  }
  
  fun getCurrentViewModel(): ViewModel {
    return mViewModel
  }
  
  /**
   * This method sets the Title on fragments that have AppBar.
   *
   * @param id Resource id of String.
   * @return String for Title.
   */
  protected fun getAppTitle(id: Int): String {
    return resources.getString(id)
  }
  
  override fun onBackPressed() {
    super.onBackPressed()
  }
}