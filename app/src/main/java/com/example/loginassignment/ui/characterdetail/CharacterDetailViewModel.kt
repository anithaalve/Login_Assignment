package com.example.loginassignment.ui.characterdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.loginassignment.data.entities.CharacterEntity
import com.example.loginassignment.data.repository.CharacterRepository
import com.example.loginassignment.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
  private val repository: CharacterRepository
) : ViewModel() {
  
  private val _id = MutableLiveData<Int>()
  
  private val _character = _id.switchMap { id ->
    repository.getCharacter(id)
  }
  val character: LiveData<Resource<CharacterEntity>> = _character
  
  
  fun start(id: Int) {
    _id.value = id
  }
  
}
