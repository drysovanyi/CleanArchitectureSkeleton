package com.dmytrorysovanyi.data.models

import com.google.gson.annotations.SerializedName

data class HelloWorldDto(
  @SerializedName("TexT")
  val text: String
)