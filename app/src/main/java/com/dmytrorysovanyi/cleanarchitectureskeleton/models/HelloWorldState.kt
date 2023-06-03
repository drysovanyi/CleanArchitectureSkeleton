package com.dmytrorysovanyi.cleanarchitectureskeleton.models

data class HelloWorldState(
    val isRefreshing: Boolean,
    val stringList: List<String>,
)
