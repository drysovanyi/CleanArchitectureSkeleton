package com.dmytrorysovanyi.data.features.hello.remote

import com.dmytrorysovanyi.common.IoDispatcher
import com.dmytrorysovanyi.data.api.ApiService
import com.dmytrorysovanyi.data.features.hello.HelloWorldRemoteDataSource
import com.dmytrorysovanyi.data.features.hello.remote.models.HelloWorldDto
import com.dmytrorysovanyi.data.mappers.HelloWorldDataMapper
import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HelloWorldRemoteDataSourceImpl @Inject constructor(
    private val api: ApiService,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val mapper: HelloWorldDataMapper,
) : HelloWorldRemoteDataSource {
    override suspend fun getRemoteHelloWorld(): Result<List<HelloWorldModel>> {
        return Result.runCatching {
            val remoteHelloWorld = withContext(ioDispatcher) {
                // api.getHelloWorld()
                listOf(HelloWorldDto(text = "Hello Old World"), HelloWorldDto(text = "Hello New World"))
            }
            mapper.mapHelloWorldDtoList(remoteHelloWorld)
        }
    }
}
