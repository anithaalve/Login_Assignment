package com.example.loginassignment.ui.characters

import androidx.lifecycle.ViewModel
import com.example.loginassignment.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
  private val repository: CharacterRepository
) : ViewModel() {
  val characters = repository.getCharacters()
}
