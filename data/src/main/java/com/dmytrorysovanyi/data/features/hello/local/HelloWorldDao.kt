package com.dmytrorysovanyi.data.features.hello.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dmytrorysovanyi.data.features.hello.local.models.HelloWorldEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HelloWorldDao {

    @Query("SELECT * FROM hello_world")
    abstract fun getAllHelloWorld(): Flow<List<HelloWorldEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertHelloWorld(list: List<HelloWorldEntity>)
}
