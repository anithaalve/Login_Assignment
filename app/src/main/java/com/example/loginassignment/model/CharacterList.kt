package com.example.loginassignment.model

import com.example.loginassignment.data.entities.CharacterEntity

data class CharacterList(
  val info: Info,
  val results: List<CharacterEntity>
)