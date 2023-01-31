package com.dmytrorysovanyi.domain.features.hello

import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel

interface HelloWorldRepository {

  suspend fun getHelloWorld(): Result<HelloWorldModel>

}