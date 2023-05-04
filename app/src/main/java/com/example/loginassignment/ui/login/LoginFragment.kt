package com.example.loginassignment.ui.login

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.loginassignment.BR
import com.example.loginassignment.R
import com.example.loginassignment.app.base.BaseFragment
import com.example.loginassignment.common.DialogUtils
import com.example.loginassignment.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
  
  override fun bindingVariable(): Int {
    return BR.loginViewModel
  }
  
  override fun viewModel(): Class<LoginViewModel> {
    return LoginViewModel::class.java
  }
  
  override fun layoutRes(): Int {
    return R.layout.fragment_login
  }
  
  override fun onViewCreated() {
    initListeners()
  }
  
  private fun initListeners() {
    viewModel.getNavigateToRegister().observe(viewLifecycleOwner, Observer {
      it.getContentIfNotHandled()?.let { // Only proceed if the event has never been handled
        displayUsersList()
        viewModel.doneNavigatingRegiter()
      }
    })
  
    viewModel.getNavigatetoUserDetails().observe(viewLifecycleOwner, Observer {
      it.getContentIfNotHandled()?.let {
        navigateUserDetails()
        viewModel.doneNavigatingUserDetails()
      }
    })
  
    viewModel.getShowErrorLiveData().observe(viewLifecycleOwner, Observer {
      it.getContentIfNotHandled()?.let {
        DialogUtils().showDialog1(it, context)
        viewModel.getShowErrorLiveData()
      }
    })
  }
  
  private fun displayUsersList() {
    val navController = Navigation.findNavController(this.requireView())
    navController.navigate(R.id.action_loginFragment_to_homeFragment)
  }
  
  private fun navigateUserDetails() {
    val bundle = Bundle()
    //bundle.putString("UserName", dataBinding.etInputUserNameLayout.text.toString())
    val navController = Navigation.findNavController(this.requireView())
    navController.navigate(R.id.action_loginFragment_to_charactersFragment)
  }

  override fun onPause() {
    super.onPause()
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
  }
  
  override fun onDestroy() {
    viewModel.getNavigateToRegister().removeObservers(this)
    viewModel.getNavigatetoUserDetails().removeObservers(this)
    viewModel.getShowErrorLiveData().removeObservers(this)
    super.onDestroy()
  }
}