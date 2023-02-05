package com.dmytrorysovanyi.data.features.hello

import com.dmytrorysovanyi.common.DefaultDispatcher
import com.dmytrorysovanyi.domain.features.hello.HelloWorldRepository
import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HelloWorldRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val remoteDataSource: HelloWorldRemoteDataSource,
    private val localSource: HelloWorldLocalDataSource,
) : HelloWorldRepository {

    override fun getHelloWorldFlow(): Flow<List<HelloWorldModel>> {
        return localSource.getAllHelloWorlds()
            .distinctUntilChanged()
            .map {
                it.ifEmpty {
                    throw IllegalStateException("Empty database")
                }
            }.flowOn(defaultDispatcher)
    }

    override suspend fun refreshHelloWorld(): Result<Unit> {
        return Result.runCatching {
            withContext(defaultDispatcher) {
                val remoteHelloWorld = remoteDataSource.getRemoteHelloWorld()
                    .getOrElse { throw it }

                localSource.insertHelloWorld(remoteHelloWorld)
            }
        }
    }
}

interface HelloWorldLocalDataSource {

    fun getAllHelloWorlds(): Flow<List<HelloWorldModel>>

    suspend fun insertHelloWorld(list: List<HelloWorldModel>)
}

interface HelloWorldRemoteDataSource {

    suspend fun getRemoteHelloWorld(): Result<List<HelloWorldModel>>
}
