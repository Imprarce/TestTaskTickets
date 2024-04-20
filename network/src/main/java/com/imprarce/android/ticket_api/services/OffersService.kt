package com.imprarce.android.ticket_api

import com.imprarce.android.ticket_api.entity.Offers
import com.imprarce.android.ticket_api.entity.OffersTickets
import retrofit2.http.GET

interface OffersService {
    @GET("offers.json")
    suspend fun getOffers(): Offers
}