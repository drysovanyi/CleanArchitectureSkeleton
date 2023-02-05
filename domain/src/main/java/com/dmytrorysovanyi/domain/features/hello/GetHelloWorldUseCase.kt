package com.dmytrorysovanyi.domain.features.hello

import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHelloWorldUseCase @Inject constructor(
    private val helloWorldRepository: HelloWorldRepository
) {

    operator fun invoke(): Flow<List<HelloWorldModel>> {
        return helloWorldRepository.getHelloWorldFlow()
    }
}
