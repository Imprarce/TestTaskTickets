package com.imprarce.android.testtasktickets.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }
}

