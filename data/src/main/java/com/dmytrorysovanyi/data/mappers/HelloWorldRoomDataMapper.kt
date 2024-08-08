package com.dmytrorysovanyi.data.mappers

import com.dmytrorysovanyi.data.features.hello.local.models.HelloWorldEntity
import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import javax.inject.Inject

class HelloWorldRoomDataMapper @Inject constructor() {

    fun mapHelloWorldEntityList(helloWorldEntity: List<HelloWorldEntity>): List<HelloWorldModel> {
        return helloWorldEntity.map { mapHelloWorldEntity(it) }
    }

    private fun mapHelloWorldEntity(helloWorldEntity: HelloWorldEntity): HelloWorldModel {
        return HelloWorldModel(helloWorldEntity.text)
    }

    fun mapHelloWorldModelList(helloWorldModel: List<HelloWorldModel>): List<HelloWorldEntity> {
        return helloWorldModel.mapIndexed { index: Int, item: HelloWorldModel ->
            mapHelloWorldModel(index, item)
        }
    }

    private fun mapHelloWorldModel(index: Int, helloWorldDto: HelloWorldModel): HelloWorldEntity {
        return HelloWorldEntity(id = index, text = helloWorldDto.text)
    }
}
