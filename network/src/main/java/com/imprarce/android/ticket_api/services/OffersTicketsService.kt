package com.imprarce.android.ticket_api.services

import com.imprarce.android.ticket_api.entity.OffersTickets
import retrofit2.http.GET

interface OffersTicketsService {
    @GET("offers_tickets.json")
    suspend fun getOffersTickets(): OffersTickets
}