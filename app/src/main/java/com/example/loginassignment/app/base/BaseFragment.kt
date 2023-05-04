package com.example.loginassignment.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<V : ViewModel, D : ViewDataBinding> : Fragment() {
  
  protected lateinit var dataBinding: D
  
  protected lateinit var viewModel: V
  
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
  protected abstract fun viewModel(): Class<V>
  
  /**
   * This method is called within the child fragment. Subclasses must override this method to
   * binds the event handling of view elements to View Model.
   */
  protected abstract fun onViewCreated()
  
  /**
   * This method is called within the child fragment. Subclasses must override this method to
   * return the layout mId for initializing child fragment data binding object.
   *
   * @return layout resource id
   */
  @LayoutRes
  protected abstract fun layoutRes(): Int
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }
  
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    dataBinding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
    return dataBinding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this).get(viewModel())
    dataBinding.setVariable(bindingVariable(), viewModel)
    dataBinding.lifecycleOwner = viewLifecycleOwner
    dataBinding.executePendingBindings()
    onViewCreated()
  }
  
  override fun onDestroyView() {
    dataBinding.unbind()
    super.onDestroyView()
  }
}