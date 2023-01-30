package com.dmytrorysovanyi.data.features

import com.dmytrorysovanyi.data.api.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class HelloWorldRepositoryImpl @Inject constructor(
  private val api: ApiService
) {
}