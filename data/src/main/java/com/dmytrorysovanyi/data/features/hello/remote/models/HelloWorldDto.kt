package com.dmytrorysovanyi.data.features.hello.remote.models

import com.google.gson.annotations.SerializedName

data class HelloWorldDto(
    @SerializedName("TexT")
    val text: String
)
