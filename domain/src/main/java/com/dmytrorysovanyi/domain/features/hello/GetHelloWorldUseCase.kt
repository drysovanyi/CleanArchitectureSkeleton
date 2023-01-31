package com.dmytrorysovanyi.domain.features.hello

import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import javax.inject.Inject

class GetHelloWorldUseCase @Inject constructor(
  private val helloWorldRepository: HelloWorldRepository
) {

  suspend operator fun invoke(): Result<HelloWorldModel>{
    return helloWorldRepository.getHelloWorld()
  }

}