package com.dmytrorysovanyi.data.mappers

import com.dmytrorysovanyi.data.models.HelloWorldDto
import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import javax.inject.Inject

class HelloWorldMapper @Inject constructor() {

  fun mapHelloWorldDto(helloWorldDto: HelloWorldDto): HelloWorldModel {
    return HelloWorldModel(helloWorldDto.text)
  }

}