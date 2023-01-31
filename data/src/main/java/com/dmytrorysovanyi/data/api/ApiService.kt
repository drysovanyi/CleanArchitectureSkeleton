package com.dmytrorysovanyi.data.api

import com.dmytrorysovanyi.data.models.HelloWorldDto

interface ApiService {

  suspend fun getHelloWorld(): HelloWorldDto = HelloWorldDto(text = "Hello World")

  companion object {
    const val BASE_URL = "https://google.com"
  }

}