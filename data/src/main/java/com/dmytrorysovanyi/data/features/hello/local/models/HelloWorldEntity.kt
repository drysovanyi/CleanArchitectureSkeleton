package com.dmytrorysovanyi.data.features.hello.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "hello_world"
)
data class HelloWorldEntity(
    @PrimaryKey
    val id: Int,
    val text: String
)
