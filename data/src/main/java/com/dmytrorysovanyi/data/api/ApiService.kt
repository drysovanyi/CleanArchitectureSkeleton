package com.dmytrorysovanyi.data.api

interface ApiService {

  suspend fun getHelloWorld(): String = "Hello World"

  companion object {
    const val BASE_URL = ""
  }

}