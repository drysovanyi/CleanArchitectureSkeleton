package com.dmytrorysovanyi.data.features.hello

import com.dmytrorysovanyi.data.api.ApiService
import com.dmytrorysovanyi.data.mappers.HelloWorldMapper
import com.dmytrorysovanyi.data.models.HelloWorldDto
import com.dmytrorysovanyi.domain.features.hello.HelloWorldRepository
import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HelloWorldRepositoryImpl @Inject constructor(
  private val api: ApiService,
  private val mapper: HelloWorldMapper
): HelloWorldRepository {
  override suspend fun getHelloWorld(): Result<HelloWorldModel> {
    return Result.runCatching {
      withContext(Dispatchers.Default) {
       val result = withContext(Dispatchers.IO) {
          // api.getHelloWorld()
         HelloWorldDto("Hello World")
        }
        mapper.mapHelloWorldDto(result)
      }
    }
  }
}