package com.dmytrorysovanyi.domain.features.hello

import javax.inject.Inject

class RefreshHelloWorldUseCase @Inject constructor(
    private val repository: HelloWorldRepository
) {

    suspend operator fun invoke() {
        repository.refreshHelloWorld()
    }
}
