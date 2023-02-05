package com.dmytrorysovanyi.data.mappers

import com.dmytrorysovanyi.data.features.hello.remote.models.HelloWorldDto
import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import javax.inject.Inject

class HelloWorldDataMapper @Inject constructor() {

    fun mapHelloWorldDtoList(helloWorldDto: List<HelloWorldDto>): List<HelloWorldModel> {
        return helloWorldDto.map { mapHelloWorldDto(it) }
    }

    private fun mapHelloWorldDto(helloWorldDto: HelloWorldDto): HelloWorldModel {
        return HelloWorldModel(helloWorldDto.text)
    }
}
