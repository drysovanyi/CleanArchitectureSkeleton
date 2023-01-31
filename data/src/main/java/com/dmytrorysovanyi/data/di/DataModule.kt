package com.dmytrorysovanyi.data.di

import com.dmytrorysovanyi.data.features.hello.HelloWorldRepositoryImpl
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

}