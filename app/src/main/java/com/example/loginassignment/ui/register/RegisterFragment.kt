package com.example.loginassignment.ui.register

import androidx.navigation.Navigation
import com.example.loginassignment.BR
import com.example.loginassignment.R
import com.example.loginassignment.app.base.BaseFragment
import com.example.loginassignment.common.DialogUtils
import com.example.loginassignment.databinding.RegisterHomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel, RegisterHomeFragmentBinding>() {
  
  override fun bindingVariable(): Int {
    return BR.registerViewModel
  }
  
  override fun viewModel(): Class<RegisterViewModel> {
    return RegisterViewModel::class.java
  }
  
  override fun layoutRes(): Int {
    return R.layout.register_home_fragment
  }
  
  override fun onViewCreated() {
    initListeners()
  }
  
  private fun initListeners() {
    //event observer like UNIT
    viewModel.getNavigationLiveData().observe(viewLifecycleOwner, {
      it.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled
        navigateBack()
      }
    })
  
    viewModel.getShowErrorLiveData().observe(viewLifecycleOwner, {
      it.getContentIfNotHandled()?.let {
        DialogUtils().showDialog1(it, context)
        viewModel.getShowErrorLiveData()
      }
    })
  }
  
    private fun navigateBack() {
    val navController = Navigation.findNavController(this.requireView())
    navController.navigate(R.id.action_registerFragment_to_loginFragment)
    viewModel.doneNavigating()
  }
  
  override fun onDestroy() {
    viewModel.getNavigationLiveData().removeObservers(this)
    viewModel.getShowErrorLiveData().removeObservers(this)
    super.onDestroy()
  }
}