package com.dmytrorysovanyi.data.api

import com.dmytrorysovanyi.data.features.hello.remote.models.HelloWorldDto

interface ApiService {

    suspend fun getHelloWorld(): List<HelloWorldDto> = listOf(HelloWorldDto(text = "Hello Old World"), HelloWorldDto(text = "Hello New World"))

    companion object {
        const val BASE_URL = "https://google.com"
    }
}
