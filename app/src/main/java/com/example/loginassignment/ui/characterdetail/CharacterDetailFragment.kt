package com.example.loginassignment.ui.characterdetail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.loginassignment.BR
import com.example.loginassignment.R
import com.example.loginassignment.app.base.BaseFragment
import com.example.loginassignment.common.utils.Resource
import com.example.loginassignment.data.entities.CharacterEntity
import com.example.loginassignment.databinding.CharacterDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<CharacterDetailViewModel, CharacterDetailFragmentBinding>() {
  
  override fun bindingVariable(): Int {
    return BR.characterDetailViewModel
  }
  
  override fun viewModel(): Class<CharacterDetailViewModel> {
    return CharacterDetailViewModel::class.java
  }
  
  override fun layoutRes(): Int {
    return R.layout.character_detail_fragment
  }
  
  override fun onViewCreated() {
    arguments?.getInt("id")?.let { viewModel.start(it) }
    setupObservers()
  }
  
  private fun setupObservers() {
    viewModel.character.observe(viewLifecycleOwner, Observer {
      when (it.status) {
        Resource.Status.SUCCESS -> {
          bindCharacter(it.data!!)
          dataBinding.progressBar.visibility = View.GONE
          dataBinding.characterCl.visibility = View.VISIBLE
        }
        
        Resource.Status.ERROR ->
          Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
        
        Resource.Status.LOADING -> {
          dataBinding.progressBar.visibility = View.VISIBLE
          dataBinding.characterCl.visibility = View.GONE
        }
      }
    })
  }
  
  private fun bindCharacter(character: CharacterEntity) {
    dataBinding.name.text = character.name
    dataBinding.species.text = character.species
    dataBinding.status.text = character.status
    dataBinding.gender.text = character.gender
    Glide.with(dataBinding.root)
      .load(character.image)
      .transform(CircleCrop())
      .into(dataBinding.image)
  }
}
