package com.dmytrorysovanyi.domain.features.hello

import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import kotlinx.coroutines.flow.Flow

interface HelloWorldRepository {

    fun getHelloWorldFlow(): Flow<List<HelloWorldModel>>

    suspend fun refreshHelloWorld(): Result<Unit>
}
