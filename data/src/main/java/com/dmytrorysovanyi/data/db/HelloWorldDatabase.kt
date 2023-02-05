package com.dmytrorysovanyi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmytrorysovanyi.data.features.hello.local.HelloWorldDao
import com.dmytrorysovanyi.data.features.hello.local.models.HelloWorldEntity

@Database(
    entities = [
        HelloWorldEntity::class
    ],
    version = 1, exportSchema = true
)
abstract class HelloWorldDatabase : RoomDatabase() {

    abstract fun helloWorldDao(): HelloWorldDao
}
