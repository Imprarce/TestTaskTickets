package com.imprarce.android.ticket_api

import com.imprarce.android.ticket_api.entity.Offers
import com.imprarce.android.ticket_api.entity.OffersTickets

interface NetworkRepository {
    suspend fun fetchOffers(): Offers

    suspend fun fetchOffersTickets(): OffersTickets

    suspend fun fetchTickets(): Tickets
}