package com.dmytrorysovanyi.data.di

import android.content.Context
import androidx.room.Room
import com.dmytrorysovanyi.data.db.HelloWorldDatabase
import com.dmytrorysovanyi.data.features.hello.local.HelloWorldDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HelloWorldDatabase {
        return Room.databaseBuilder(
            context,
            HelloWorldDatabase::class.java,
            "hello_world.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAffirmationsDao(
        coinsDatabase: HelloWorldDatabase
    ): HelloWorldDao = coinsDatabase.helloWorldDao()
}
