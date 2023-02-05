package com.dmytrorysovanyi.data.di

import com.dmytrorysovanyi.data.features.hello.HelloWorldLocalDataSource
import com.dmytrorysovanyi.data.features.hello.HelloWorldRemoteDataSource
import com.dmytrorysovanyi.data.features.hello.HelloWorldRepositoryImpl
import com.dmytrorysovanyi.data.features.hello.local.HelloWorldLocalDataSourceImpl
import com.dmytrorysovanyi.data.features.hello.remote.HelloWorldRemoteDataSourceImpl
import com.dmytrorysovanyi.domain.features.hello.HelloWorldRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindHelloWorldRepository(repositoryImpl: HelloWorldRepositoryImpl): HelloWorldRepository

    @Binds
    abstract fun bindHelloWorldRemoteDataSource(dataSource: HelloWorldRemoteDataSourceImpl): HelloWorldRemoteDataSource

    @Binds
    abstract fun bindHelloWorldLocalDataSource(dataSource: HelloWorldLocalDataSourceImpl): HelloWorldLocalDataSource
}
