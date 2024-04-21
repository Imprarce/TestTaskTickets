package com.imprarce.android.testtasktickets.di

import android.app.Application
import android.content.Context
import com.imprarce.android.ticket_api.NetworkService
import com.imprarce.android.ticket_api.ReadFromAssets
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideReadFromAssets(app: Application): ReadFromAssets{
        return ReadFromAssets(app.applicationContext)
    }
}