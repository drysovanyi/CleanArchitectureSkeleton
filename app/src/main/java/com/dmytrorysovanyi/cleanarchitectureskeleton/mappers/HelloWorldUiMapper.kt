package com.dmytrorysovanyi.cleanarchitectureskeleton.mappers

import com.dmytrorysovanyi.domain.features.hello.models.HelloWorldModel
import javax.inject.Inject

class HelloWorldUiMapper @Inject constructor() {

    fun mapHelloWorldModelToUi(model: List<HelloWorldModel>): List<String> {
        return model.map {
            with(it) {
                text
            }
        }
    }
}
