package com.dmytrorysovanyi.data.features.hello.local

import com.dmytrorysovanyi.common.IoDispatcher
import com.dmytrorysovanyi.data.features.hello.HelloWorldLocalDataSource
import com.dmytrorysovanyi.data.mappers.HelloWorldRoomDataMapper
import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HelloWorldLocalDataSourceImpl @Inject constructor(
    private val helloWorldDao: HelloWorldDao,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val mapper: HelloWorldRoomDataMapper,
) : HelloWorldLocalDataSource {
    override fun getAllHelloWorlds(): Flow<List<HelloWorldModel>> {
        return helloWorldDao.getAllHelloWorld()
            .flowOn(ioDispatcher)
            .map {
                mapper.mapHelloWorldEntityList(it)
            }
    }

    override suspend fun insertHelloWorld(list: List<HelloWorldModel>) {
        val mappedList = mapper.mapHelloWorldModelList(list)
        withContext(ioDispatcher) {
            helloWorldDao.insertHelloWorld(mappedList)
        }
    }
}
