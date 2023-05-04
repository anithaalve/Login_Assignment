package com.example.loginassignment.model

import com.google.gson.annotations.SerializedName

data class User(
  @SerializedName("id") val id: Int,
  @SerializedName("email") val email: String,
  @SerializedName("first_name") val first_name: String,
  @SerializedName("last_name") val last_name: String,
  @SerializedName("avatar") val avatar: String
)