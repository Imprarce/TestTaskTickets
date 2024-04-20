package com.imprarce.android.ticket_api

import com.google.gson.GsonBuilder
import com.imprarce.android.ticket_api.services.OffersTicketsService
import com.imprarce.android.ticket_api.services.TicketsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object NetworkService {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val ticketsService: TicketsService = Retrofit.Builder()
        .baseUrl("file:///android_asset/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(TicketsService::class.java)

    val offersService: OffersService = Retrofit.Builder()
        .baseUrl("file:///android_asset/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(OffersService::class.java)

    val offersTicketsService: OffersTicketsService = Retrofit.Builder()
        .baseUrl("file:///android_asset/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(OffersTicketsService::class.java)
}