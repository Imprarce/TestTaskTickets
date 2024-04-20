package com.imprarce.android.ticket_api.services

import com.imprarce.android.ticket_api.Tickets
import retrofit2.http.GET

interface TicketsService {
    @GET("tickets.json")
    suspend fun getTickets(): Tickets
}